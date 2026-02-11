package busan_dining.dagil.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID 자동 사용
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(unique = true)
    private String refreshToken;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public String getRefreshToken() {
        return refreshToken;
    }
}
