package com.first.project.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class parameters {
    @Id
    @Column(name = "Id")
    private String Id;

//    @Column(name = "keys")
//    @NotNull
//    private String keys;

    @Column(name = "value")
    @NotNull
    private float value;

    public parameters() {
    }

    public parameters(String id, float value) {
        Id = id;
        this.value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
    this.value=value;
    }
}
