package com.first.project.classes_for_requestbody;

public class OrderStatus {

    private int date;

    private String message;
    private String email;
    public OrderStatus() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderStatus(int date, String message, String email) {
        this.date =date ;

        this.message = message;
        this.email=email;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
