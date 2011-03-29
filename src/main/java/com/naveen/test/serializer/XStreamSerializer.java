package com.naveen.test.serializer;

import com.thoughtworks.xstream.XStream;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 27/2/11
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class XStreamSerializer implements Serializer<XStream>
{
    private XStream serializer;

    public <T> String getStringObject(T objectToBeSerialized)
    {
        return serializer.toXML(objectToBeSerialized);
    }

    public <T> T getObjectFromString(String jsonString)
    {
        return (T) serializer.fromXML(jsonString);
    }

    public void setSerializer(XStream serializer) {
        this.serializer = serializer;
    }

    public XStream getSerializer() {
        return serializer;
    }
}
