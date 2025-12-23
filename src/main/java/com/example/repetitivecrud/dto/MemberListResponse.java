package com.example.repetitivecrud.dto;

import java.util.List;

public class MemberListResponse {
    // 속
    private Integer count;
    private List<MemberAllResponse> memberList;

    // 생
    public MemberListResponse(Integer count, List<MemberAllResponse> memberList) {
        this.count = count;
        this.memberList = memberList;
    }

    // Getter
    public Integer getCount() {
        return count;
    }

    public List<MemberAllResponse> getMemberList() {
        return memberList;
    }

    // 내부 클래스
    public static class MemberAllDto {// 속
        private Long id;
        private String name;

        // 생
        public MemberAllDto(Long id, String name) {
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
}
