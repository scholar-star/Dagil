package busan_dining.dagil.controllers;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.dto.SignupDTO;
import busan_dining.dagil.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupDTO signupDTO) {
        return authService.signUp(signupDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(String loginID) { // 로그인 아이디를 이용, refreshToken을 찾아 제거
        return authService.logout(loginID);
    }
}
