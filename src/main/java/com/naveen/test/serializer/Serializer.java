package com.naveen.test.serializer;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 6/3/11
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Serializer<S> {
    public void setSerializer(S serializer);
    public S getSerializer();
    public <T> String getStringObject(T object);
    public <T> T getObjectFromString(String object);
}
