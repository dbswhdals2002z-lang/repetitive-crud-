package com.example.repetitivecrud.controller;

import com.example.repetitivecrud.dto.*;
import com.example.repetitivecrud.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class); // @Slf4j -> Lombok 사용 시 , 이 어노테이션으로 해결 가능
    // 속
    private final MemberService memberService;

    // 생
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /// 회원 생성 -> 회원가입 API
    @PostMapping
    public ResponseEntity<ApiResponse<MemberRegisterResponse>> memberRegisterApi(@RequestBody MemberRegisterRequest memberRegisterRequest) {
//        String name = memberCreateRequest.getName();
        log.info("email: {}", memberRegisterRequest.getEmail());
        log.info("password: {}", memberRegisterRequest.getPassword());

        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberRegisterResponse responseDto = memberService.registerMember(memberRegisterRequest);

        // 2. Api 래퍼 생성 (ApiResponse 만들기)
        ApiResponse<MemberRegisterResponse> apiResponse = new ApiResponse("created", 201, responseDto);

        // 3. ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberRegisterResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED); // (201 CREATED)
        return response;


    }

    /// 회원 단건조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberApi(@PathVariable Long memberId) {
        log.info("controller - 회원 단건조회, memberId: {}", memberId);

        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberResponse responseDto = memberService.getMember(memberId);

        // 2. Api 래퍼 생성 (ApiResponse 만들기)
        ApiResponse<MemberResponse> apiResponse = new ApiResponse("success", 200, responseDto);

        // 3.ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK); // (200 OK)
        return response;
    }

    /// 회원 다건조회
    @GetMapping
    public ResponseEntity<ApiResponse<MemberListResponse>> getAllMemberApi() {
        log.info("controller - 회원 다건조회");

        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberListResponse responseListDto = memberService.getAllMember();

        // 2. Api 래퍼 생성 (ApiResponse 만들기)
        ApiResponse<MemberListResponse> apiResponse = new ApiResponse("success", 200, responseListDto);

        // 3.ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberListResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;


    }

    ///  회원 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberUpdateResponse>> updateMemberApi(@PathVariable Long memberId, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        log.info("controller - 회원 수정");

        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberUpdateResponse responseDto = memberService.updateMember(memberId, memberUpdateRequest);

        // 2. Api 래퍼 생성 (ApiResponse 만들기)
        ApiResponse<MemberUpdateResponse> apiResponse = new ApiResponse("updated", 200, responseDto);

        // 3.ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberUpdateResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;


    }

    ///  회원 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberDeleteResponse>> deleteMemberApi(@PathVariable Long memberId) {
        log.info("controller - 회원 삭제, memberId: {}", memberId);

        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberDeleteResponse responseDto = memberService.deleteMember(memberId);

        // 2. Api 래퍼 생성 (ApiResponse 만들기)
        ApiResponse<MemberDeleteResponse> apiResponse = new ApiResponse("deleted", 200, responseDto);

        // 3.ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberDeleteResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;


    }

}
