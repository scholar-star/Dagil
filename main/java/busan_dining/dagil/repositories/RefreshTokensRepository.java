package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.RefreshTokens;
import busan_dining.dagil.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, UUID> {
    public RefreshTokens findByRefreshToken(String refreshToken);
    public RefreshTokens findByUsers(Users user);
}
