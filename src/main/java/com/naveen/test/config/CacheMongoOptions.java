package com.naveen.test.config;

import com.mongodb.MongoOptions;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 27/2/11
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CacheMongoOptions extends MongoOptions{
    public void setConnectionsPerHost(int connectionsPerHost)
    {
        this.connectionsPerHost = connectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier)
    {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(int maxWaitTime)
    {
        this.maxWaitTime = maxWaitTime;
    }

    public void setConnectTimeout(int connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout)
    {
        this.socketTimeout = socketTimeout;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry)
    {
        this.autoConnectRetry = autoConnectRetry;
    }

    public void init()
    {
        reset();
    }

}
