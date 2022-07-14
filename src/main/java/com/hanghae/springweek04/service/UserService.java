package com.hanghae.springweek04.service;

import com.hanghae.springweek04.dto.SignupRequestDto;
import com.hanghae.springweek04.model.User;
import com.hanghae.springweek04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {    // WebSecurityConfig에서 만든 것.//  BCryptPasswordEncoder()를 DI한다.
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 요청 처리
    public String registerUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        // 비밀번호
        String password = requestDto.getPassword();
        // 패스워드 확인
        String checkPassword = requestDto.getCheckPassword();


        //  `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성
        String usernamePattern = "^[a-zA-Z0-9].{2,}$";
        // 비밀번호 최소 4자 이상
        String passwordPattern = "^([a-zA-Z0-9]).{3,}$";


        boolean usernameMatch = Pattern.matches(usernamePattern, username);

        if (!usernameMatch) {
            throw new IllegalArgumentException("최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성해주세요.");
        }

        //  닉네임 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // 비밀번호 조건 확인
        boolean passwordMatch = Pattern.matches(passwordPattern, password);

        if (!passwordMatch) {
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상이어야 합니다.");
        }


        //  패스워드에 닉네임과 같은 값이 포함 된 경우 회원가입에 실패
        if (password.contains(username)) {
            throw new IllegalArgumentException("회원가입 실패");

//            return "redirect:/user/signup?error";
        }

        // 패스워드 확인
        if (!(password.equals(checkPassword))) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());


        User user = new User(username, password);
        userRepository.save(user);


        return username;
    }
}

//    public String loginUser()
//}