package com.hanghae.springweek04.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {


    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^([a-zA-Z0-9]).{4,}$", message = "최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^([a-zA-Z0-9]).{4,}$", message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String checkPassword;



}

