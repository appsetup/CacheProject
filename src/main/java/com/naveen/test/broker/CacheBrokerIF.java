package com.naveen.test.broker;

import com.mongodb.DBObject;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CacheBrokerIF {
    public <T> void save(T object);
    public <T> void delete(T object);
    public <T> void saveAll(Collection<T> objects);
    public <T> void deleteAll(Collection<T> objects);
    public <T> List<T> search(DBObject object, DBObject requiredFields, Class<T> requiredType);
    public <T> List<T> search(DBObject object, Class<T> requiredType);
}
