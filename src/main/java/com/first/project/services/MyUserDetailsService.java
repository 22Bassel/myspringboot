package com.first.project.services;

import com.first.project.entities.user;
import com.first.project.services.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private Request service;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user userdata=service.getuser(username);

        return new User(userdata.getUsername(),userdata.getPassword(),new ArrayList<>());

    }


}
