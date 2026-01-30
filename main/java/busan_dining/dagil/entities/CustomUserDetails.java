package busan_dining.dagil.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final Users user;
    private final Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getUsername() {
        return user.getLoginID();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
