package busan_dining.dagil.jwt;

import busan_dining.dagil.dto.TokenDTO;
import busan_dining.dagil.entities.RefreshTokens;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.RefreshTokensRepository;
import busan_dining.dagil.repositories.UserInfoRepository;
import busan_dining.dagil.repositories.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final RefreshTokensRepository refreshTokensRepository;
    private final UsersRepository usersRepository;
    private final UserInfoRepository userInfoRepository;
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   RefreshTokensRepository refreshTokensRepository,
                   UsersRepository usersRepository) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshTokensRepository = refreshTokensRepository;
        this.usersRepository = usersRepository;
    }

    public TokenDTO generateToken(String nickname) {
        String accessToken = Jwts.builder()
                .subject(nickname)
                .issuedAt(new Date()) // 발행 시간
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30)) // 만료 시간 : 발행 시간 + 30분
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(nickname)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24*14)) // 만료 시간 : 발행 시간 + 14일
                .signWith(secretKey)
                .compact();

        Users findUser = userInfoRepository.
        RefreshTokenSave(refreshToken, findUser);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void RefreshTokenSave(String refreshToken, Users user) {
        RefreshTokens refreshTokens = RefreshTokens.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        refreshTokensRepository.save(refreshTokens);
    }

    private Jws<Claims> extractClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    private boolean validateToken(String token) {
        Jws<Claims> claims = extractClaims(token);
        if (claims.getPayload().getExpiration().before(new Date())) {
            return false;
        } else {
            return true;
        }
    }
}
