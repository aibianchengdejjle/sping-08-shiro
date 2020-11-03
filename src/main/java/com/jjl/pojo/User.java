package com.jjl.pojo;

public class User {
    private String perms;

    public User() {
    }

    public User(String perms, int id, String pwd) {
        this.perms = perms;
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "perms='" + perms + '\'' +
                ", id=" + id +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    private int id;


    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
