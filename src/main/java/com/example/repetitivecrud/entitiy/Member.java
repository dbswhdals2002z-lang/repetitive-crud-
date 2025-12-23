package com.example.repetitivecrud.entitiy;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean isDeleted;

    protected Member() {
    } // JPA 기본 생성자

    public Member(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /// 회원 이름 수정 메서드
    public Member updateMemberName(String newMemberName) {
        this.name = newMemberName;
        return this;
    }

    ///  회원 삭제처리를 위한 메서드
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
