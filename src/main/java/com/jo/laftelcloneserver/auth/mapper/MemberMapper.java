package com.jo.laftelcloneserver.auth.mapper;

import com.jo.laftelcloneserver.auth.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDTO findById(String memberId);
    void insertMember(MemberDTO memberDTO);
}
