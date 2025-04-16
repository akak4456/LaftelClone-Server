package com.jo.laftelcloneserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MemberDTO 에서 민감한 memberPw를 뺀것이라 생각하면 됨
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
    private String memberId;
    private String memberEmail;
    private String memberRole;
}
