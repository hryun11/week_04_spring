package com.hanghae.springweek04.member;


import com.hanghae.springweek04.dto.SignupRequestDto;
import com.hanghae.springweek04.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class MemberServiceTest {


    @Autowired
    private UserService userService;
    private static Validator validator;

    @BeforeEach
    public void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @DisplayName("닉네임 3자리 미만인가?")
    @Test
    void nicknameLessThenThreeLetter() {
// 성공
        SignupRequestDto request1 = new SignupRequestDto("user1", "1111", "1111");

// 닉네임 3자리 미만
        Set<ConstraintViolation<SignupRequestDto>> result1 = validator.validate(
                new SignupRequestDto("user2", "1111", "1111"));

        Set<ConstraintViolation<SignupRequestDto>> result2 = validator.validate(
                new SignupRequestDto("user3", "1111", "1111"));

        Assertions.assertThat(result1).size().isGreaterThan(0);
        Assertions.assertThat(result2).size().isGreaterThan(0);
    }

    @DisplayName("비밀번호가 4자 미만")
    @Test
    void passwordLessThenFourLetter() {
        Set<ConstraintViolation<SignupRequestDto>> result1 = validator.validate(
                new SignupRequestDto("user4", "111", "111"));

        Set<ConstraintViolation<SignupRequestDto>> result2 = validator.validate(
                new SignupRequestDto("user5", "111", "111"));

        Assertions.assertThat(result1).size().isGreaterThan(0);
        Assertions.assertThat(result2).size().isGreaterThan(0);
    }

    @DisplayName("비밀번호에 닉네임이 포함되는 경우")
    @Test
    void containNickname() {
// 성공
        SignupRequestDto request1 = new SignupRequestDto("user1", "1111", "1111");
// 비밀번호에 닉네임이 포함된 경우
        SignupRequestDto request6 = new SignupRequestDto("user6", "user6", "user6");
        SignupRequestDto request7 = new SignupRequestDto("user7", "user312", "user12");

// Exception Test
        Assertions.assertThatNoException().isThrownBy(() -> userService.registerUser(request1));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> userService.registerUser(request6));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> userService.registerUser(request7));
    }

    @DisplayName("비밀번호가 다른 경우")
    @Test
    void wrongPasswordCheckPassword() {
// 성공
        SignupRequestDto request1 = new SignupRequestDto("user1", "1111", "1111");

// 비밀번호가 다름
        SignupRequestDto request8 = new SignupRequestDto("user8", "1111", "1112");
        SignupRequestDto request9 = new SignupRequestDto("user9", "1111", "1112");

        Assertions.assertThatNoException().isThrownBy(() -> userService.registerUser(request1));

// Exception Test
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> userService.registerUser(request8));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> userService.registerUser(request9));
    }
}
