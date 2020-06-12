package kr.hs.dsm_scarfs.util;

import kr.hs.dsm_scarfs.exception.ExpiredTokenException;
import kr.hs.dsm_scarfs.exception.InvalidTokenException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static String SECURITY_KEY = System.getenv("SECRET_KEY_BASE");

    private static String generateToken(Object data, Long expire, String type) {
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setHeaderParam("typ", "JWT")
                .claim("user_id", data)
                .claim("type", type)
                .setExpiration(new Date(nowMillis + expire))
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY.getBytes());

        return builder.compact();
    }

    public static String getAccessToken(Object data) {

        return generateToken(data, 1000L * 3600 * 24, "access_token");
    }
    public static String getRefreshToken(Object data) {

        return generateToken(data, 1000L * 3600 * 24 * 30, "refresh_token");
    }

    public static Integer parseToken(String token) {
        String result;
        try {
            result = Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().get("user_id").toString();
            if (!Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().get("type").equals("access_token")) throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }
        return Integer.parseInt(result);
    }

    public static Integer parseRefreshToken(String token) {
        try {
            if(!Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().get("type").equals("refresh_token")) throw new InvalidTokenException();
            token = Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().get("user_id").toString();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }
        return Integer.parseInt(token);
    }
}
