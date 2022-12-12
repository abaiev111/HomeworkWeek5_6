package com.gmail.aba.task1;

public class TrafficViolation {
    private String dateTime;
    private String firstName;
    private String lastName;
    private String type;
    private long fineAmount;

    public String getDate_time() {
        return dateTime;
    }

    public void setDate_time(String date_time) {
        this.dateTime = date_time;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getFine_amount() {
        return fineAmount;
    }

    public void setFine_amount(long fine_amount) {
        this.fineAmount = fine_amount;
    }
}

