package com.example.demo.entry;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_info", schema = "zhfinfo", catalog = "")
public class UserInfo {
    private int id;
    private Integer userId;
    private String userName;
    private String password;
    private String picture;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id &&
                Objects.equals (userId, userInfo.userId) &&
                Objects.equals (userName, userInfo.userName) &&
                Objects.equals (password, userInfo.password) &&
                Objects.equals (picture, userInfo.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, userId, userName, password, picture);
    }
}
