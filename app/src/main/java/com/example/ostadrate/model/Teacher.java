package com.example.ostadrate.model;

import com.squareup.moshi.Json;

public class Teacher {

    @Json(name = "teacher_id")
    private int personalCode;

    @Json(name = "name")
    private String name;

    @Json(name = "image")
    private String imageUrl;

    @Json(name = "university")
    private String university;

    public Teacher(int personalCode, String name, String imageUrl, String university) {
        this.personalCode = personalCode;
        this.name = name;
        this.imageUrl = imageUrl;
        this.university = university;
    }

    public void setPersonalCode(int personalCode) {
        this.personalCode = personalCode;
    }

    public int getPersonalCode() {
        return personalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
