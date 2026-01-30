package busan_dining.dagil.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String accessToken = parseToken(req);
        if (accessToken != null) {
            if (jwtUtil.validateToken(accessToken)) {
                if (jwtUtil.expireJwtToken(accessToken)) {
                    Authentication authentication = jwtUtil.createAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Token 만료 시 -> RefreshToken 비교, 재발급
                    String refreshToken = null;
                    Cookie[] cookies = req.getCookies(); // 쿠키에서 RefreshToken
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("refresh_token")) {
                                refreshToken = cookie.getValue();
                            }
                        }
                    }
                    // refreshToken 유효성 확인
                    if (jwtUtil.validateRefreshToken(refreshToken)) {
                        String loginID = jwtUtil.extractClaims(accessToken).getPayload().getSubject();
                        String newToken = jwtUtil.generateAccessToken(loginID);
                        Authentication authentication = jwtUtil.createAuthentication(newToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    private String parseToken(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Bearer 이후부터 전달
        }
        return null;
    }
}
