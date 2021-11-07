package com.first.project.services;

import java.io.UnsupportedEncodingException;
import  java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class password_hashing {
    public String encoder_pass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        return password;
/**
        MessageDigest md=MessageDigest.getInstance("MD5");
        byte[] pass_bytes=password.getBytes("UTF-8");

        byte []new_pass=md.digest(pass_bytes);


        return String.valueOf(new_pass);
    **/
    }


    public byte[] decode_pass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md=MessageDigest.getInstance("SHA-512");
        byte[] pass_bytes=password.getBytes("UTF-8");

        byte []new_pass=md.digest(pass_bytes);


        return new_pass;
    }
}
