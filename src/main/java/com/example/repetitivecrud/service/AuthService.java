package com.example.repetitivecrud.service;

import com.example.repetitivecrud.dto.MemberLoginRequest;
import com.example.repetitivecrud.dto.MemberLoginResponse;
import com.example.repetitivecrud.entitiy.Member;
import com.example.repetitivecrud.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // 생성자 주입
    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {
        log.info("AuthService.login()");
        // 1. 데이터 준비
        String email = memberLoginRequest.getEmail();
        String password = memberLoginRequest.getPassword();

        // 2. 회원조회 - 받은 email 로 회원을 검색하기
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 3. 비밀번호 검증 - 받아온 비밀번호와 기존 회원의 비밀번호가 일치하는 확인
        String encodedPassword = foundMember.getPassword();
        boolean match = passwordEncoder.matches(password, encodedPassword);

        if (!match) {
            throw new RuntimeException("invalid credentials");
        }
        // 로그인 다 끝나고 토큰이 필요한 것임! -> 이제 토큰을 만들려면 JWT를 학습해야함.
        /// 숙제
        // 4. 토큰 만들기
        //?????? 토큰에 어떤데이터를 담아줘야할까 ?
        // password 절대x , - email, name, id(pk: 식별자)


        Long foundMemberId = foundMember.getId();
        String foundMemberEmail = foundMember.getEmail();
        String foundMemberName = foundMember.getName();

        log.info("==== 토큰 생성 시작 ====");
        log.info("회원 ID: {}", foundMemberId);
        log.info("회원 이메일: {}", foundMemberEmail);
        log.info("회원 이름: {}", foundMemberName);

        String token = jwtService.createToken(foundMemberId, foundMemberEmail, foundMemberName);


//        String token = "ThisIsJwtToken....";
        MemberLoginResponse responseDto = new MemberLoginResponse(token);



        return responseDto;

        // 인증인가
        // 1. 회원가입 (o)
        // 2. 로그인 -> 토큰 생성(발급) (0)
        // 3. 인증정보 활용 -> 토큰 검증 ()
    }
}
