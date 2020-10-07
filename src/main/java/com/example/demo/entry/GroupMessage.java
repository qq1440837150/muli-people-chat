package com.example.demo.entry;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "group_message", schema = "zhfinfo", catalog = "")
public class GroupMessage {
    private int id;
    private String content;
    private String picture;
    private Timestamp recordTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "record_time")
    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        GroupMessage that = (GroupMessage) o;
        return id == that.id &&
                Objects.equals (content, that.content) &&
                Objects.equals (picture, that.picture) &&
                Objects.equals (recordTime, that.recordTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, content, picture, recordTime);
    }
}
