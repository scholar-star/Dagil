package busan_dining.dagil.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class RefreshTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @Column(unique = true)
    private String refreshToken;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}
