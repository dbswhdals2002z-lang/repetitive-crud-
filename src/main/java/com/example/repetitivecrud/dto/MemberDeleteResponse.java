package com.example.repetitivecrud.dto;

public class MemberDeleteResponse {
    // 속
    private Long id;

    // 생
    public MemberDeleteResponse(Long id) {
        this.id = id;
    }

    // Getter
    public Long getId() {
        return id;
    }
}
