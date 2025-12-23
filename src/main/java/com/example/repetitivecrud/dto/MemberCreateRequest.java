package com.example.repetitivecrud.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberCreateRequest {
    // 속
    private String name;

    // 생
    @JsonCreator
    public MemberCreateRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}
