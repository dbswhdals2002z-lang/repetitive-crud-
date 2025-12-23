package com.example.repetitivecrud.dto;

public class MemberResponse {

    // 속
    private Long id;
    private String name;

    // 생
    public MemberResponse(Long id, String name) {
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
