package busan_dining.dagil.services;

import busan_dining.dagil.dto.LoginDTO;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDTO loginDTO) {
        Users findUser = usersRepository.findByLoginID(loginDTO.loginID());
        if (findUser == null) {
            return "Invalid username";
        } else if (!passwordEncoder.matches(loginDTO.password(), findUser.password)) {
            return "Invalid password";
        } else {

        }
    }
}