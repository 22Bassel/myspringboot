package com.first.project.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int Id;
    @Column(name = "name")
    private String name=" ";

    @Column(name = "price")
    @NotNull
    private float price=0;

    @Column(name = "seatnumber")
    @NotNull
    private int seatnumber=0;
    @Column(name = "date")
    @NotNull
    private int date;
    @Column(name = "owner_name")
    @NotNull
    private String owner_name=" ";

    public car() {
    }

    public car(int Id) {
        this.Id = Id;

    }

    public car(int Id, float price, int seatnumber, int date, String owner_name) {
        this.Id=Id;
        this.price = price;
        this.seatnumber = seatnumber;
        this.date = date;
        this.owner_name = owner_name;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

}
