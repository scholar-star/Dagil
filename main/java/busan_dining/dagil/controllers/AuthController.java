package busan_dining.dagil.controllers;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.dto.SignupDTO;
import busan_dining.dagil.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignupDTO signupDTO) {
        return authService.signUp(signupDTO);
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping
    public ResponseEntity<String> logout(String loginID) { // 로그인 아이디를 이용, refreshToken을 찾아 제거

    }
}
