package com.jo.laftelcloneserver.auth.controller;

import com.jo.laftelcloneserver.auth.dto.JoinRequestDTO;
import com.jo.laftelcloneserver.auth.dto.JwtToken;
import com.jo.laftelcloneserver.auth.dto.LoginRequestDTO;
import com.jo.laftelcloneserver.auth.dto.ReLoginRequestDTO;
import com.jo.laftelcloneserver.auth.service.AuthService;
import com.jo.laftelcloneserver.response.EmptyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public JwtToken getMemberProfile(
            @Valid @RequestBody LoginRequestDTO request
    ) {
        return authService.login(request);
    }

    @PostMapping("relogin")
    public JwtToken reLogin(
            @Valid @RequestBody ReLoginRequestDTO request
    ) {
        return authService.reLogin(request);
    }

    @PostMapping("join")
    public EmptyResponse joinMember(
            @Valid @RequestBody JoinRequestDTO request
    ) {
        authService.tryJoin(request);
        return new EmptyResponse();
    }
}