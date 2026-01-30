package busan_dining.dagil.jwt;

import busan_dining.dagil.dto.TokenDTO;
import busan_dining.dagil.entities.RefreshTokens;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.RefreshTokensRepository;
import busan_dining.dagil.repositories.UserInfoRepository;
import busan_dining.dagil.repositories.UsersRepository;
import busan_dining.dagil.services.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final CustomUserDetailService customUserDetailService;
    private final RefreshTokensRepository refreshTokensRepository;
    private final UsersRepository usersRepository;
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   RefreshTokensRepository refreshTokensRepository,
                   UsersRepository usersRepository,
                   UserInfoRepository userInfoRepository,
                   CustomUserDetailService customUserDetailService) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshTokensRepository = refreshTokensRepository;
        this.usersRepository = usersRepository;
        this.customUserDetailService = customUserDetailService;
    }

    public TokenDTO generateToken(String loginID) {
        String accessToken = Jwts.builder()
                .subject(loginID)
                .issuedAt(new Date()) // 발행 시간
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30)) // 만료 시간 : 발행 시간 + 30분
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(loginID)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24*14)) // 만료 시간 : 발행 시간 + 14일
                .signWith(secretKey)
                .compact();

        Users user = usersRepository.findByLoginID(loginID);
        RefreshTokenSave(refreshToken, user); // RefreshToken DB에 저장
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateAccessToken(String loginID) {
        String accessToken = Jwts.builder()
                .subject(loginID)
                .issuedAt(new Date()) // 발행 시간
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30)) // 만료 시간 : 발행 시간 + 30분
                .signWith(secretKey)
                .compact();
        return accessToken;
    }

    private void RefreshTokenSave(String refreshToken, Users user) {
        RefreshTokens refreshTokens = RefreshTokens.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        refreshTokensRepository.save(refreshTokens);
    }

    public Jws<Claims> extractClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    public boolean expireJwtToken(String token) {
        Jws<Claims> claims = extractClaims(token);
        Date expiration = claims.getPayload().getExpiration();
        if (expiration.before(new Date())) {
            return false;
        }
        return true;
    }

    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        } else {
            try {
                Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
                return true; // Token 자체 서명 유효 시
            } catch (JwtException je) {
                return false; // Token 자체 서명이 유효하지 않을 경우.
            }
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        // DB에서 꺼내 refreshToken 확인
        String existRefreshToken = refreshTokensRepository.findByRefreshToken(refreshToken).getRefreshToken();
        if (refreshToken.equals(existRefreshToken)) {
            return true;
        }
        return false;
    }

    public Authentication createAuthentication(String token) {
        // Claim 추출 후, Authentication에 넣을 UserDetails 생성
        Jws<Claims> jwtClaims = extractClaims(token);
        Claims claims = jwtClaims.getBody();

        // Claim에 저장해놓은 loginID를 추출, 이전에 구현한 CustomUserDetailService의 loadUserByUsername으로 UserDetails 생성
        String loginID = claims.getSubject();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginID);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return authentication;
    }

    public void removeRefreshToken(Users user) {
        // refreshToken DB에서 삭제(로그아웃 시)
        RefreshTokens refreshTokens = refreshTokensRepository.findByUsers(user);
        refreshTokensRepository.delete(refreshTokens);
    }
}
