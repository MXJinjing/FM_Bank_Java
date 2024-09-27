package cn.edu.lzu.fmbank.commons.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {

    private String bid;

    private String username;

    private String id;

    private String tel;

    private String sex;

    @JSONField(format = "yyyy-MM-dd")
    private Date birth;

    private Double balance;

    public User() {
    }

    public User(String bid, String username, String id, String tel, String sex, Date birth, Double balance) {
        this.bid = bid;
        this.username = username;
        this.id = id;
        this.tel = tel;
        this.sex = sex;
        this.birth = birth;
        this.balance = balance;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
};

