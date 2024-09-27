package cn.edu.lzu.fmbank.server.user.services;

import java.sql.Connection;

public class BasicService {
    protected String bid;
    protected Connection con;

    public BasicService(String bid, Connection con) {
        this.bid = bid;
        this.con = con;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
