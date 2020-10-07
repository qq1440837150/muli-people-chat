package com.example.demo.entry;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group_user_relative", schema = "zhfinfo", catalog = "")
public class GroupUserRelative {
    private int id;
    private Integer power;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "power")
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        GroupUserRelative that = (GroupUserRelative) o;
        return id == that.id &&
                Objects.equals (power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, power);
    }
}
