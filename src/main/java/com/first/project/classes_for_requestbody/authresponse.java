package com.first.project.classes_for_requestbody;

public class authresponse {

    private final String jwt;

    public authresponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
