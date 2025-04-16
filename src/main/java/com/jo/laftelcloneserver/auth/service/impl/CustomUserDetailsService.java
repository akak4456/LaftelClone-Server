package com.jo.laftelcloneserver.auth.service.impl;

import com.jo.laftelcloneserver.auth.dto.MemberDTO;
import com.jo.laftelcloneserver.auth.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.findById(username);
        if(memberDTO == null) {
            throw new UsernameNotFoundException("해당하는 유저가 없습니다.");
        }
        return memberDTO;
    }
}
