package com.hanghae.springweek04.controller;

import com.hanghae.springweek04.dto.SignupRequestDto;
import com.hanghae.springweek04.dto.UserInfoDto;
import com.hanghae.springweek04.security.UserDetailsImpl;
import com.hanghae.springweek04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

//    // 로그인 api
//    @GetMapping("/user/login")
//    public String loginUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        userService.loginUser();
//    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Validated SignupRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("===getFieldErrors==="+errors.getFieldError());
        }
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, false);
    }
}



    // 회원가입 api
    //    - 닉네임, 비밀번호, 비밀번호 확인을 request에서 전달받기
    //    - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
    //    - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
    //    - 비밀번호 확인은 비밀번호와 정확하게 일치하기
    //    - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 response에 포함하기


    // 로그인 api
    //    - 닉네임, 비밀번호를 request에서 전달받기
    //    - 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 에러 메세지를 response에 포함하기

