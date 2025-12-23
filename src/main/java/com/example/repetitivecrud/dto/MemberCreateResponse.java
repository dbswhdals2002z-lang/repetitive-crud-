package com.example.repetitivecrud.dto;

public class MemberCreateResponse {

    // 속
    private Long id;

    // 생
    public MemberCreateResponse(Long id) {
        this.id = id;
    }

    // Getter
    public Long getId() {
        return id;
    }
}
