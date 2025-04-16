package com.jo.laftelcloneserver.auth.service.impl;

import com.jo.laftelcloneserver.auth.dto.*;
import com.jo.laftelcloneserver.auth.mapper.MemberMapper;
import com.jo.laftelcloneserver.auth.service.AuthService;
import com.jo.laftelcloneserver.exception.BusinessException;
import com.jo.laftelcloneserver.general.security.JwtUtil;
import com.jo.laftelcloneserver.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    @Override
    @Transactional(readOnly = true)
    public JwtToken login(LoginRequestDTO dto) {
        String id = dto.getMemberId();
        String password = dto.getMemberPw();
        MemberDTO member = memberMapper.findById(id);
        if(member == null) {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if(!encoder.matches(password, member.getMemberPw())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        MemberInfoDTO info = modelMapper.map(member, MemberInfoDTO.class);

        JwtToken token = jwtUtil.createJwtToken(info);
        return token;
    }

    @Override
    @Transactional
    public void tryJoin(JoinRequestDTO request) {
        // TODO 구체적인 예외 정하기
        request.setMemberPw(encoder.encode(request.getMemberPw()));
        MemberDTO member = modelMapper.map(request, MemberDTO.class);
        member.setMemberRole("USER");
        memberMapper.insertMember(member);
    }

    @Override
    public JwtToken reLogin(ReLoginRequestDTO request) {
        if(jwtUtil.validateToken(request.getRefreshToken())) {
            return jwtUtil.createJwtToken(jwtUtil.getMemberInfo(request.getRefreshToken()));
        } else {
            throw new BusinessException(ErrorCode.REFRESH_NOT_VALID);
        }
    }
}