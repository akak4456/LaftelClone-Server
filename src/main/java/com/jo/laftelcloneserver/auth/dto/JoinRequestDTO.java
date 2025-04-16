package com.jo.laftelcloneserver.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// TODO 구체적인 입력 조건 정하기
public class JoinRequestDTO {
    @NotNull(message = "아이디 입력은 필수입니다.")
    private String memberId;
    @NotNull(message = "이메일 입력은 필수입니다.")
    private String memberEmail;
    @NotNull(message = "비밀번호 입력은 필수입니다.")
    private String memberPw;
    @NotNull(message = "비밀번호 확인 입력은 필수입니다.")
    private String memberPwCheck;
    @NotNull(message = "닉네임 입력은 필수입니다.")
    private String memberNickname;
    @NotNull(message = "소개 입력은 필수입니다.")
    private String memberIntroduce;
}
