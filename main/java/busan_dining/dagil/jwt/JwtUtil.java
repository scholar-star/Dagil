package busan_dining.dagil.jwt;

import busan_dining.dagil.dto.TokenDTO;
import busan_dining.dagil.entities.CustomUserDetails;
import busan_dining.dagil.entities.RefreshTokens;
import busan_dining.dagil.entities.Users;
import busan_dining.dagil.repositories.RefreshTokensRepository;
import busan_dining.dagil.repositories.UserInfoRepository;
import busan_dining.dagil.repositories.UsersRepository;
import busan_dining.dagil.services.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
    private final UserInfoRepository userInfoRepository;
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   RefreshTokensRepository refreshTokensRepository,
                   UsersRepository usersRepository,
                   UserInfoRepository userInfoRepository,
                   CustomUserDetailService customUserDetailService) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshTokensRepository = refreshTokensRepository;
        this.usersRepository = usersRepository;
        this.userInfoRepository = userInfoRepository;
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
        RefreshTokenSave(refreshToken, user);
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

    public Jws<Claims> extractClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        } else {
            try {
                Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
                if (claims.getPayload().getExpiration().before(new Date())) return false;
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }

    private Authentication createAuthentication(String token) {
        // Claim 추출 후, Authentication에 넣을 UserDetails 생성
        Jws<Claims> jwtClaims = extractClaims(token);
        Claims claims = jwtClaims.getBody();

        // Claim에 저장해놓은 loginID를 추출, 이전에 구현한 CustomUserDetailService의 loadUserByUsername으로 UserDetails 생성
        String loginID = claims.getSubject();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginID);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return authentication;
    }
}
