package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, UUID> {

}
