package com.pose.oauth.controller;

import com.pose.oauth.dto.request.auth.EmailCertificationRequestDto;
import com.pose.oauth.dto.request.auth.IdCheckRequestDto;
import com.pose.oauth.dto.response.auth.EmailCertificationResponseDto;
import com.pose.oauth.dto.response.auth.IdCheckResponseDto;
import com.pose.oauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (@RequestBody @Valid IdCheckRequestDto req) {
        ResponseEntity<? super IdCheckResponseDto> res = authService.idCheck(req);
        return res;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification (@RequestBody @Valid EmailCertificationRequestDto dto) {
        ResponseEntity<? super EmailCertificationResponseDto> res = authService.emailCertification(dto);
        return res;
    }
}
