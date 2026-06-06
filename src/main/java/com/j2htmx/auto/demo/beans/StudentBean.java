package com.j2htmx.auto.demo.beans;

public class StudentBean {
    Integer rollNo;
    String name;

    public StudentBean(Integer rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
