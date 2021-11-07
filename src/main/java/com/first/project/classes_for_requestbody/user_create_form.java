package com.first.project.classes_for_requestbody;

import com.first.project.entities.Role;
import com.sun.istack.NotNull;


public class user_create_form {

    @NotNull
    private String email = "";

    @NotNull
    private String password = "";

    @NotNull
    private String passwordRepeated = "";

    @NotNull
    private Role role = Role.USER;
}
