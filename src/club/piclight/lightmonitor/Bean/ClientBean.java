package club.piclight.lightmonitor.Bean;

import java.util.Date;

public class ClientBean {
    private int id;
    private String userSession;
    private String clientName;
    private String clientIP;
    private String clientInfo;
    private int pkgNum;
    private Date lastestOnline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserSession() {
        return userSession;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public int getPkgNum() {
        return pkgNum;
    }

    public void setPkgNum(int pkgNum) {
        this.pkgNum = pkgNum;
    }

    public Date getLastestOnline() {
        return lastestOnline;
    }

    public void setLastestOnline(Date lastestOnline) {
        this.lastestOnline = lastestOnline;
    }
}
