package com.example.repetitivecrud.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberLoginRequest {

    private String email;
    private String password;

    @JsonCreator
    public MemberLoginRequest(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password

    ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(){return email;}

    public String getPassword(){return password;}
}
