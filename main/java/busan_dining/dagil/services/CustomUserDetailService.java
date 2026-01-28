package busan_dining.dagil.services;

import busan_dining.dagil.entities.CustomUserDetails;
import busan_dining.dagil.entities.Role;
import busan_dining.dagil.entities.UserRole;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.UserRoleRepository;
import busan_dining.dagil.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService {
    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users finduser = usersRepository.findByLoginID(username);

        UserRole roleMapping = userRoleRepository.findByUser(finduser);
        List<Role> roles = roleMapping.getRoles();

        // UserDetails 구현체에 getAuthorities가 들어가기 때문에, Role들을 Authorities로 변환
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        UserDetails userDetail = new CustomUserDetails(finduser, authorities);
        return userDetail;
    }
}
