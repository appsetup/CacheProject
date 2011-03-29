package com.naveen.test.dao;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CacheDAO
{
    private Mongo mongo;

    private String collectionName;

    private String dbName;


    public void save(DBObject object)
    {
        mongo.getDB(dbName).getCollection(collectionName).save(object);
    }

    public void delete(DBObject object)
    {
        mongo.getDB(dbName).getCollection(collectionName).remove(object);
    }

    public DBCursor search(DBObject object, DBObject fields)
    {
        return mongo.getDB(dbName).getCollection(collectionName).find(object,fields);
    }

    public Mongo getMongo() {
        return mongo;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
