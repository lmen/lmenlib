package com.slib.rmt.backtesting.externalsystems.jms;

public class VarJmsConfig {

    private String url = "failover://(tcp://localhost:61616)";
    private String queueInName = "VAR_IN_BT";
    private String queueOutName = "VAR_OUT_BT";
    private long timeout = 30 * 1000;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout( long timeout ) {
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getQueueInName() {
        return queueInName;
    }

    public void setQueueInName( String queueInName ) {
        this.queueInName = queueInName;
    }

    public String getQueueOutName() {
        return queueOutName;
    }

    public void setQueueOutName( String queueOutName ) {
        this.queueOutName = queueOutName;
    }

}
