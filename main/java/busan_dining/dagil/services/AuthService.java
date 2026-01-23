package busan_dining.dagil.services;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.entities.UserInfo;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.jwt.JwtUtil;
import busan_dining.dagil.repositories.UserInfoRepository;
import busan_dining.dagil.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> login(LoginDTO loginDTO) {
        Users findUser = usersRepository.findByLoginID(loginDTO.loginID());
        if (findUser == null) {
            return new ResponseEntity<String>("Invalid loginID", HttpStatus.UNAUTHORIZED);
        } else if (!passwordEncoder.matches(loginDTO.password(), findUser.password)) {
            return new ResponseEntity<String>("Invalid password", HttpStatus.UNAUTHORIZED);
        } else {
            UserInfo info = userInfoRepository.findByUsers(findUser);
            return new ResponseEntity<String>(jwtUtil.generateToken(info.nickname).toString(), HttpStatus.OK);
        }
    }
}