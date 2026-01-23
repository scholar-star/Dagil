package busan_dining.dagil.controllers;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
