package com.example.repetitivecrud.service;

import com.example.repetitivecrud.dto.*;
import com.example.repetitivecrud.entitiy.Member;
import com.example.repetitivecrud.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberService.class); // @Slf4j -> Lombok 사용 시 , 이 어노테이션으로 해결 가능
    // 속
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 생
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /// 회원 생성 -> 회원가입 로직
    @Transactional
    public MemberRegisterResponse registerMember(MemberRegisterRequest memberRegisterRequest) {
        // 1. 데이터 준비
        String name = memberRegisterRequest.getName();
        String email = memberRegisterRequest.getEmail();
        String password = memberRegisterRequest.getPassword();


        // 2. 이메일 중복 여부 체크 -> 존재하는지 안 하는지 확인 (존재여부 확인) 조회할 필요x
        Boolean exist = memberRepository. existsByEmail(email);
        if (exist) {
            throw new RuntimeException("member already exists");
        }

        // 3. 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(password);
//        String result = encoder.encode("myPassword"); -> 형태

        // 2. 회원 엔티티 생성-> 주의할 점: 파라미터 값에 password 넣을 때 암호환 된 비밀번호(encodePassword)를 넣어줘야함.
        Member newMember = new Member(name, email, encodePassword);

        // 3. 저장(레포지토리 활용)
        Member savedMember = memberRepository.save(newMember);

        // 4. response dto 생성
        Long savedMemberId = savedMember.getId();
        MemberRegisterResponse responseDto = new MemberRegisterResponse(savedMemberId);
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
    @Transactional
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
    @Transactional
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
