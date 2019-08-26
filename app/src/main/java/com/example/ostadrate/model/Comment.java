package com.example.ostadrate.model;

import com.squareup.moshi.Json;

public class Comment {

    @Json(name = "teacher_id")
    private int teacherId;

    @Json(name = "name")
    private String name;

    @Json(name = "comment")
    private String comment;

    public Comment(int teacherId, String name, String comment) {
        this.teacherId = teacherId;
        this.name = name;
        this.comment = comment;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
