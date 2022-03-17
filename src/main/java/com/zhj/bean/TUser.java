package com.zhj.bean;

import java.util.Date;

public class TUser {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private String sex;

    private String status;

    private Date registertime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public TUser(Integer id, String username, String realname, String password, String sex, String status, Date registertime) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.sex = sex;
        this.status = status;
        this.registertime = registertime;
    }

    public TUser() {
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", registertime=" + registertime +
                '}';
    }
}