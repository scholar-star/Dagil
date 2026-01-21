package busan_dining.dagil.jwt;

import busan_dining.dagil.dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final
    private Key secretKey;

    public JwtUtil(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private TokenDTO generateToken(String loginID) {
        String accessToken = Jwts.builder()
                .subject(loginID)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(loginID)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
