package busan_dining.dagil;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.dto.SignupDTO;
import busan_dining.dagil.dto.TokenDTO;
import busan_dining.dagil.jwt.JwtUtil;
import busan_dining.dagil.services.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 데이터를 다룬 후 서버를 끄면 롤백
public class AuthTests {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void SignupAndLoginTest() {
        SignupDTO signupDTO = SignupDTO.builder()
                .nickname("사용자")
                .email("email@email.com")
                .loginID("user")
                .password("1111").build();


        Assertions.assertEquals(new ResponseEntity<String>("회원가입 완료", HttpStatus.OK),
                authService.signUp(signupDTO));

        LoginDTO right = new LoginDTO("user", "1111");
        LoginDTO wrong = new LoginDTO("wrong", "1111");
        LoginDTO wrong2 = new LoginDTO("user","1234");

        Assertions.assertInstanceOf(TokenDTO.class, authService.login(right).getBody());
        Assertions.assertEquals(new ResponseEntity<String>("Invalid loginID", HttpStatus.UNAUTHORIZED),
                authService.login(wrong));
        Assertions.assertEquals(new ResponseEntity<String>("Invalid password", HttpStatus.UNAUTHORIZED),
                authService.login(wrong2));

    }
}
