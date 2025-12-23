package com.example.repetitivecrud.service;

import com.example.repetitivecrud.dto.*;
import com.example.repetitivecrud.entitiy.Member;
import com.example.repetitivecrud.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberService.class); // @Slf4j -> Lombok 사용 시 , 이 어노테이션으로 해결 가능
    // 속
    private final MemberRepository memberRepository;

    // 생
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    /// 회원 생성
    public MemberCreateResponse createMember(MemberCreateRequest memberCreateRequest) {
        // 1. 데이터 준비
        String name = memberCreateRequest.getName();

        // 2. 회원 엔티티 생성
        Member newMember = new Member(name);

        // 3. 저장(레포지토리 활용)
        Member savedMember = memberRepository.save(newMember);

        // 4. response dto 생성
        Long savedMemberId = savedMember.getId();
        MemberCreateResponse responseDto = new MemberCreateResponse(savedMemberId);
        return responseDto;

    }

    /// 회원 단건 조회
    public MemberResponse getMember(Long memberId) {
        log.info("service - 회원 단건조회 , memberId: {}", memberId);

        // 회원 단건 조회(상세 조회)
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // dto를 만들기 위한 데이터 준비
        Long foundMemberId = foundMember.getId();
        String foundMemberName = foundMember.getName();

        // dto 만들기
        MemberResponse responseDto = new MemberResponse(foundMemberId, foundMemberName);
        return responseDto;
    }

    /// 회원 다건 조회
    public MemberListResponse getAllMember() {
        log.info("service - 회원 다건조회");

        // 회원 목록 조회
        List<Member> memberList = memberRepository.findByIsDeletedFalse();
        int count = memberList.size();

        // 내부 dto
        List<MemberAllResponse> memberAllResponse = new ArrayList<>();
        for (Member member : memberList) {
            log.info("memberId: {}", member.getId());

            // 데이터 준비
            Long foundMemberId = member.getId();
            String foundMemberName = member.getName();

            // 내부 dto 만들기
            MemberAllResponse responseAll = new MemberAllResponse(foundMemberId, foundMemberName);
            memberAllResponse.add(responseAll);
        }

        // 외부 dto 만들기
        MemberListResponse responseList = new MemberListResponse(count, memberAllResponse);
        return responseList;
    }

    ///  회원 수정
    public MemberUpdateResponse updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        log.info("service- 회원 수정");

        // 회원 조회
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // dto를 만들기 위한 데이터 준비
        String newMemberName = memberUpdateRequest.getName();
        Member updateMember = foundMember.updateMemberName(newMemberName);
        Long updateMemberId = updateMember.getId();

        // dto 만들기
        MemberUpdateResponse responseDto = new MemberUpdateResponse(updateMemberId);
        return responseDto;
    }

    ///  회원 삭제
    public MemberDeleteResponse deleteMember(Long memberId) {
        log.info("service - 회원 삭제");

        // 회원 조회
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // dto를 만들기 위한 데이터 준비, dto 만들기 -> 삭제는 필요없음. (그냥 데이터에 삭제했습니다만 보여주면 되기 때문)
        foundMember.setDeleted(true);
        log.info("service - 삭제 완료");

        // dto 반환
        return new MemberDeleteResponse(foundMember.getId());

    }


}
