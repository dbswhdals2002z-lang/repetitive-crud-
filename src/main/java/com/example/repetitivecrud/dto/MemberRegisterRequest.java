package com.example.repetitivecrud.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberRegisterRequest {
    // 속
    private String name;
    private String email;
    private String password;

    // 생
    @JsonCreator
    public MemberRegisterRequest(@JsonProperty("name") String name,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("password") String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

