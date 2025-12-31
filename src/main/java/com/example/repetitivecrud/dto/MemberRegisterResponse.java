package com.example.repetitivecrud.dto;

public class MemberRegisterResponse {

    // 속
    private Long id;

    // 생
    public MemberRegisterResponse(Long id) {
        this.id = id;
    }

    // Getter
    public Long getId() {
        return id;
    }
}
