package com.first.project.services;

import com.first.project.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Optional;

@Service

public class user_services {


    @Autowired
    final repositoris repositoris;

    public user_services(com.first.project.services.repositoris repositoris) {
        this.repositoris = repositoris;
    }


    /////

/**
    public Optional<user> getUserById(long id) {

        return repositoris.findOne(id);
    }
**/


    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public  Collection<user> getUsers() {
    return repositoris.getAllUsers();
    }

        /////


    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public boolean log_in_user(user user) throws Exception {

        user user2=repositoris.user_getOne(user.getUsername());

        if(user2!=null) {

            if(new BCryptPasswordEncoder().matches(user.getPassword(),user2.getPassword()))
                return true;
           else {
               throw new Exception("incorect");
            }
        }
        throw new Exception("incorect");

    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public boolean founduser(int id){

        return repositoris.user_existsById(id);

    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public boolean findbyname(String name){
       return repositoris.user_existbyname(name);

    }

    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,timeout = 5,rollbackFor = Exception.class)
    public void add_new_user(user user) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        repositoris.user_save(user);

    }


    @Transactional(isolation= Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = Exception.class)
    public user getbyname(String name){
        return repositoris.user_getOne(name);

        //return new user();
    }

}