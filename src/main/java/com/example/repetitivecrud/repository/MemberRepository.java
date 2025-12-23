package com.example.repetitivecrud.repository;

import com.example.repetitivecrud.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 단건조회, 수정, 삭제에서 사용하는 메서드
    Optional<Member> findByIdAndIsDeletedFalse(Long memberId);

    // 다건조회에서 사용하는 메서드
    List<Member> findByIsDeletedFalse();
}
