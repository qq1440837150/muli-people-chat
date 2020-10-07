package com.example.demo.entry;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group_info", schema = "zhfinfo", catalog = "")
public class GroupInfo {
    private int id;
    private Integer groupId;
    private String password;
    private Byte isPassword;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
    @Column(name = "is_password")
    public Byte getIsPassword() {
        return isPassword;
    }

    public void setIsPassword(Byte isPassword) {
        this.isPassword = isPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        GroupInfo groupInfo = (GroupInfo) o;
        return id == groupInfo.id &&
                Objects.equals (groupId, groupInfo.groupId) &&
                Objects.equals (password, groupInfo.password) &&
                Objects.equals (isPassword, groupInfo.isPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, groupId, password, isPassword);
    }
}
