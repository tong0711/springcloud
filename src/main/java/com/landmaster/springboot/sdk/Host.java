package com.landmaster.springboot.sdk;

/**
 * Created by tdl on 2017/6/15.
 */
public class Host {
    private String host;
    private int port;
    private String rootPath;

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

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
