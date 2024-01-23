package com.beauver.zermelo;

public class ZermeloData {
    private String school;
    private String accessCode;

    public ZermeloData(String school, String accessCode) {
        this.school = school;
        this.accessCode = accessCode;
    }

    // Getters and setters (or use lombok for automatic generation)
    public String getSchool() {
        return school;
    }

    public String getAccessCode() {
        return accessCode;
    }

}
