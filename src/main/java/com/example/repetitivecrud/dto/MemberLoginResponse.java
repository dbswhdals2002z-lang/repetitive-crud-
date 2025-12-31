package com.example.repetitivecrud.dto;

public class MemberLoginResponse {

    private String token;

    public MemberLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
