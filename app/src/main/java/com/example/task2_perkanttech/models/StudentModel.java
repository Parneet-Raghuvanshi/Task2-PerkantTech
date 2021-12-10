package com.example.task2_perkanttech.models;

public class StudentModel {

    String name;
    String code;
    String score;

    public StudentModel() {
    }

    public StudentModel(String name, String code, String score) {
        this.name = name;
        this.code = code;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
