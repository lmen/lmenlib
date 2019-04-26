package com.slib.rmt.backtesting.externalsystems.webservices;

public class VarWSConfig {

    private int timeOutMs = 5 * 1000;
    private String varBaseUrl = "http://localhost:8080/var";
    private String user;
    private String pwd;

    public int getTimeOutMs() {
        return timeOutMs;
    }

    public void setTimeOutMs( int timeOutMs ) {
        this.timeOutMs = timeOutMs;
    }

    public String getVarBaseUrl() {
        return varBaseUrl;
    }

    public void setVarBaseUrl( String varBaseUrl ) {
        this.varBaseUrl = varBaseUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser( String user ) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd( String pwd ) {
        this.pwd = pwd;
    }

}
