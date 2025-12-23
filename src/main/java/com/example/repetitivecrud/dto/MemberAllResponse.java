package com.example.repetitivecrud.dto;

public class MemberAllResponse {
    // 속
    private Long id;
    private String name;

    // 생
    public MemberAllResponse(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    // Getter
    public Long getId() {
        return id;

    }

    public String getName() {
        return name;
    }
}
