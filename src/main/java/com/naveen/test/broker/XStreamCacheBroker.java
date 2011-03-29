package com.naveen.test.broker;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.naveen.test.Employee;
import com.naveen.test.dao.CacheDAO;
import com.naveen.test.serializer.Serializer;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class XStreamCacheBroker implements CacheBrokerIF
{
    private CacheDAO cacheDAO;
    private Serializer<XStream> serializer;


    public <T> void save(T object) {
        String stringObject = serializer.getStringObject(object);
        stringObject = stringObject.replaceAll("\""+object.getClass().getName() + "\":", "");
        stringObject = stringObject.substring(stringObject.indexOf("{")+1,stringObject.lastIndexOf("}"));
        cacheDAO.save((DBObject) JSON.parse(stringObject));
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

    public <T> List<T> search(DBObject object, DBObject requiredFields, Class<T> requiredType) {
        DBCursor search = cacheDAO.search(object,requiredFields);
        List<T> returnedData = new ArrayList<T>();
        while(search.hasNext())
        {
            DBObject next = search.next();
            System.out.println(next.toString());
            String objectString = next.toString().replaceAll("\"_id.*}\\s+,", "");
            T objectFromString = (T)serializer.getObjectFromString("{" + requiredType.getName() +":"+ objectString + "}");
            returnedData.add(objectFromString);
        }
        return returnedData;
    }

    public <T> List<T> search(DBObject object, Class<T> requiredType) {
        DBCursor search = cacheDAO.search(object,null);
        List<T> returnedData = new ArrayList<T>();
        while(search.hasNext())
        {
            DBObject next = search.next();

            System.out.println(next.toString());

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
