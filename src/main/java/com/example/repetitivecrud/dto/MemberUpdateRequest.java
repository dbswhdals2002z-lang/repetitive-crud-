package com.example.repetitivecrud.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberUpdateRequest {

    // 속
    private String name;

    // 생
    @JsonCreator
    public MemberUpdateRequest(@JsonProperty String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}
