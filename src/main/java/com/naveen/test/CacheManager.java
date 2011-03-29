package com.naveen.test;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.naveen.test.broker.CacheBrokerIF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class CacheManager
{
    private CacheBrokerIF cacheBroker;

    public CacheBrokerIF getCacheBroker() {
        return cacheBroker;
    }

    public void setCacheBroker(CacheBrokerIF cacheBroker) {
        this.cacheBroker = cacheBroker;
    }

    public <T> void addCache(T object)
    {
        cacheBroker.save(object);
    }

    public <T> void deleteCache(T object)
    {
        cacheBroker.delete(object);
    }

    public <T> List<T> search(DBObject object, DBObject requiredFields, Class<T> requiredType) {
        return cacheBroker.search(object,requiredFields,requiredType);
    }

    public <T> List<T> search(DBObject object, Class<T> requiredType) {
        return cacheBroker.search(object,null);
    }

}
