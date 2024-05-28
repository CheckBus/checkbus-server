package dgsw.bus.checkbus.global.security.interceptor.jwt;

import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.user.application.port.out.ReadUserPort;
import dgsw.bus.checkbus.user.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 3600 * 24; // 24시간
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 3600 * 24 * 3; // 3일

    private final ReadUserPort readUserPort;

    private Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(String email, Long time, TokenType type) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("type", type);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(String email) {
        return generateToken(email, ACCESS_TOKEN_EXPIRE_TIME, TokenType.ACCESSTOKEN);
    }

    public String generateRefreshToken(String email) {
        return generateToken(email, REFRESH_TOKEN_EXPIRE_TIME, TokenType.REFRESHTOKEN);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalArgumentException, UnsupportedJwtException, MalformedJwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw BackendException.of(ExceptionCode.TOKEN_EXPIRED);
        } catch (IllegalArgumentException e) {
            throw BackendException.of(ExceptionCode.TOKEN_NOT_PROVIDED);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            throw BackendException.of(ExceptionCode.INVALID_TOKEN);
        } catch (EventException e) {
            throw e;
        }
    }

    public TokenType checkTokenType(String token) {
        if ("REFRESHTOKEN".equals(extractAllClaims(token).get("type"))) {
            return TokenType.REFRESHTOKEN;
        } else {
            return TokenType.ACCESSTOKEN;
        }
    }

    public User getUserByToken(String token) {
        return readUserPort.getUserByEmail(extractAllClaims(token).get("email").toString());
    }
}
