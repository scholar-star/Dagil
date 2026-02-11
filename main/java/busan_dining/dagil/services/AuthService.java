package busan_dining.dagil.services;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.dto.SignupDTO;
import busan_dining.dagil.dto.TokenDTO;
import busan_dining.dagil.entities.Role;
import busan_dining.dagil.entities.UserInfo;
import busan_dining.dagil.entities.UserRole;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.jwt.JwtUtil;
import busan_dining.dagil.repositories.RoleRepository;
import busan_dining.dagil.repositories.UserInfoRepository;
import busan_dining.dagil.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> signUp(SignupDTO signupDTO) {
        String encryptedPassword = passwordEncoder.encode(signupDTO.password());
        Users user = Users.builder()
                .loginID(signupDTO.loginID())
                .password(encryptedPassword)
                .build();
        usersRepository.save(user);

        UserInfo userInfo = UserInfo.builder()
                .user_id(user)
                .email(signupDTO.email())
                .foreigner(signupDTO.traveler())
                .nickname(signupDTO.nickname())
                .build();

        userInfoRepository.save(userInfo);
        Role newUserRole = roleRepository.findByid(2L); // ROLE_USER
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(newUserRole)
                .build();

        return new ResponseEntity<String>("회원가입 완료", HttpStatus.OK);
    }

    public ResponseEntity<?> login(LoginDTO loginDTO) {
        Users findUser = usersRepository.findByLoginID(loginDTO.loginID());
        if (findUser == null) {
            return new ResponseEntity<String>("Invalid loginID", HttpStatus.UNAUTHORIZED);
        } else if (!passwordEncoder.matches(loginDTO.password(), findUser.getPassword())) {
            return new ResponseEntity<String>("Invalid password", HttpStatus.UNAUTHORIZED);
        } else {
            TokenDTO tokenDTO = jwtUtil.generateToken(findUser.getLoginID());
            return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> logout(String loginID) {
        Users findUser = usersRepository.findByLoginID(loginID);
        jwtUtil.removeRefreshToken(findUser);
        return new ResponseEntity<String>("로그아웃 완료", HttpStatus.OK);
    }
}