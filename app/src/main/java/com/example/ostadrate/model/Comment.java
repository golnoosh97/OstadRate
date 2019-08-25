package com.example.ostadrate.model;

public class Comment {

    private int teacherId;
    private String name;
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
