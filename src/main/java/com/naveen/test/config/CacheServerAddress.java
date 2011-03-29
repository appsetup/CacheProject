package com.naveen.test.config;

import com.mongodb.ServerAddress;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 28/2/11
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CacheServerAddress
{

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerAddress getServerAddress() throws UnknownHostException {
        return new ServerAddress(host, port);
    }
}
