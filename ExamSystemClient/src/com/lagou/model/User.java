package com.lagou.model;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = -1225113970581096678L;

    private String userName; // 用于描述用户名信息
    private String password; // 用于描述密码信息
    private String type; // 用于描述用户类型信息

    public User() {
    }

    public User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
