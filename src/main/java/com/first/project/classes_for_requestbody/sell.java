package com.first.project.classes_for_requestbody;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

public class sell {

    int car_id;
    float price;
    String customer_name;



    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

}
