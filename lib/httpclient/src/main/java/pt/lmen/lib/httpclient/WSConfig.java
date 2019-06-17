package pt.lmen.lib.httpclient;

public class WSConfig {

    private int timeOutMs = 5 * 1000;
    private String appBaseUrl = "http://localhost:8080/app";
    private String user;
    private String pwd;

    public int getTimeOutMs() {
        return timeOutMs;
    }

    public void setTimeOutMs( int timeOutMs ) {
        this.timeOutMs = timeOutMs;
    }

    public String getAppBaseUrl() {
		return appBaseUrl;
	}

	public void setAppBaseUrl(String appBaseUrl) {
		this.appBaseUrl = appBaseUrl;
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
