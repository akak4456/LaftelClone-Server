package com.jo.laftelcloneserver.auth.service;

import com.jo.laftelcloneserver.auth.dto.JoinRequestDTO;
import com.jo.laftelcloneserver.auth.dto.JwtToken;
import com.jo.laftelcloneserver.auth.dto.LoginRequestDTO;
import com.jo.laftelcloneserver.auth.dto.ReLoginRequestDTO;
import jakarta.validation.Valid;

public interface AuthService {
    JwtToken login(LoginRequestDTO dto);

    void tryJoin(@Valid JoinRequestDTO request);

    JwtToken reLogin(@Valid ReLoginRequestDTO request);
}
