package com.jo.laftelcloneserver.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "아이디 입력은 필수입니다.")
    private String memberId;


    @NotNull(message = "패스워드 입력은 필수입니다.")
    private String memberPw;
}