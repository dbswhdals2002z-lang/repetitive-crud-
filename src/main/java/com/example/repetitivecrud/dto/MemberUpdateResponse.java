package com.example.repetitivecrud.dto;

public class MemberUpdateResponse {
    // 속
    private Long id;

    // 생
    public MemberUpdateResponse(Long id){
        this.id = id;
    }

    // Getter
    public Long getId(){
        return id;
    }

}
