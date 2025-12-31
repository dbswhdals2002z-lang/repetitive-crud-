package com.example.repetitivecrud.controller;

import com.example.repetitivecrud.dto.ApiResponse;
import com.example.repetitivecrud.dto.MemberLoginRequest;
import com.example.repetitivecrud.dto.MemberLoginResponse;
import com.example.repetitivecrud.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberLoginResponse>> loginApi(@RequestBody MemberLoginRequest memberLoginRequest) {
        // 1. 서비스 호출 (서비스에서 반환 데이터 준비)
        // 응답 dto 생성
        MemberLoginResponse responseDto= authService.login(memberLoginRequest);

        // 2. Api 래퍼 생성 (ApiResponse 만들기) // HttpStatus.OK.value() -> Integer status이기 때문에
        ApiResponse<MemberLoginResponse> apiResponse = new ApiResponse<MemberLoginResponse>("success", HttpStatus.OK.value(),responseDto);

        // 3. ResponseEntity 생성 (ResponseEntity<> 만들기 -> ResponseEntity 반환)
        ResponseEntity<ApiResponse<MemberLoginResponse>> response = new ResponseEntity<ApiResponse<MemberLoginResponse>>(apiResponse, HttpStatus.OK);
        return response;
    }
}
