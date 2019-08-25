package com.example.ostadrate.model;

import com.squareup.moshi.Json;

public class Rate {

    @Json(name = "teacher_id")
    private int teacherId;

    @Json(name = "behaviour")
    private double behaviour;

    @Json(name = "teaching")
    private double teachingQuality;

    @Json(name = "grading")
    private double grading;

    public Rate(int teacherId, double behaviour, double teachingQuality, double grading) {
        this.teacherId = teacherId;
        this.behaviour = behaviour;
        this.teachingQuality = teachingQuality;
        this.grading = grading;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public double getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(double behaviour) {
        this.behaviour = behaviour;
    }

    public double getTeachingQuality() {
        return teachingQuality;
    }

    public void setTeachingQuality(double teachingQuality) {
        this.teachingQuality = teachingQuality;
    }

    public double getGrading() {
        return grading;
    }

    public void setGrading(double grading) {
        this.grading = grading;
    }
}
