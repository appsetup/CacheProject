package com.naveen.test.broker;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.naveen.test.annotation.CacheId;
import com.naveen.test.dao.CacheDAO;
import com.naveen.test.query.Query;
import com.naveen.test.serializer.Serializer;
import com.thoughtworks.xstream.XStream;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonCacheBroker implements CacheBrokerIF
{
    private CacheDAO cacheDAO;
    private Serializer serializer;


    public <T> void save(T object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Query query = getQueryToCheckIfAlreadyPresent(object, fields);
        String stringObject = serializer.getStringObject(object);
        stringObject = stringObject.replaceAll("\""+object.getClass().getName() + "\":", "");
        stringObject = stringObject.substring(stringObject.indexOf("{")+1,stringObject.lastIndexOf("}"));
        DBObject objectToSave = (DBObject) JSON.parse(stringObject);
        DBObject dbObject = cacheDAO.searchById((DBObject) JSON.parse(query.getQuery()));
        if(dbObject !=null)
        {
            Object id = dbObject.get("_id");
            objectToSave.put("_id",id);
        }
        System.out.println();
        cacheDAO.save(objectToSave);
    }

    private <T> Query getQueryToCheckIfAlreadyPresent(T object, Field[] fields) {
        Query query = null;
        for (Field field : fields) {
            if(field.isAnnotationPresent(CacheId.class))
            {
               field.setAccessible(true);
               if(query == null)
               {
                   query = new Query();
               }
                try {
                    query.field(field.getName()).equalTo(String.valueOf(field.get(object)));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Invalid cache id ",e);
                }
            }
        }
        if(query ==null)
        {
            throw new IllegalArgumentException("No Cache Id defined for object :"+object.getClass().getName());
        }
        return query;
    }

    public <T> void delete(T object) {
        String stringObject = serializer.getStringObject(object);
        stringObject = stringObject.replaceAll("\""+object.getClass().getName() + "\":", "");
        stringObject = stringObject.substring(stringObject.indexOf("{")+1,stringObject.lastIndexOf("}"));
        cacheDAO.delete((DBObject) JSON.parse(stringObject));
    }

    public <T> void saveAll(Collection<T> objects) {
        for (T object : objects) {
            save(object);
        }
    }

    public <T> void deleteAll(Collection<T> objects) {
        for (T object : objects) {
            delete(object);
        }
    }

    public <T> List<T> search(Query query, Class<T> requiredType) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id",0);
        DBObject queryDBObject = (DBObject) JSON.parse(query.getQuery());
        DBCursor search = cacheDAO.search(queryDBObject,dbObject);
        List<T> returnedData = new ArrayList<T>();
        while(search.hasNext())
        {
            DBObject next = search.next();
            System.out.println(next.toString());
            T objectFromString = (T)serializer.getObjectFromString("{" + requiredType.getName() +":"+ next.toString() + "}");
            returnedData.add(objectFromString);
        }
        return returnedData;
    }

    public CacheDAO getCacheDAO() {
        return cacheDAO;
    }


    public void setCacheDAO(CacheDAO cacheDAO) {
        this.cacheDAO = cacheDAO;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer<XStream> serializer) {
        this.serializer = serializer;
    }
}
