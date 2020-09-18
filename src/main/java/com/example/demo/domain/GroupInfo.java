package com.example.demo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GroupInfo {

    private Integer id;
    @NotNull
    @Min(value = 3,message = "长度不能小于三")
    private Integer groupId;

    private String password;
    private Boolean isPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getIsPassword() {
        return isPassword;
    }

    public void setIsPassword(Boolean isPassword) {
        this.isPassword = isPassword;
    }
}