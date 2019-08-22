package com.example.ostadrate;

public class Rate {

    private int teacherId;
    private double behaviour;
    private double teachingQuality;
    private double mark;

    public Rate(int teacherId, double behaviour, double teachingQuality, double mark) {
        this.teacherId = teacherId;
        this.behaviour = behaviour;
        this.teachingQuality = teachingQuality;
        this.mark = mark;
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

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
