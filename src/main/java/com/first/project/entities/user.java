package com.first.project.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long Id;
    @Column(name = "username")
    @NotNull
//    @Size(min = 1, max = 45)
    private String username;

    @Column(name = "password")
    @NotNull
//    @Size(min = 1, max = 45)
    private String password;
/**
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role roleId;
**/
    public user() {
    }


    public user(String username, String password) {
        this.username = username;
        this.password = password;
   //     this.roleId = roleId;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
