package com.jo.laftelcloneserver.general.security;

import com.jo.laftelcloneserver.auth.dto.JwtToken;
import com.jo.laftelcloneserver.auth.dto.MemberInfoDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * https://sjh9708.tistory.com/170 를 참고하여 제작함
 * [JWT 관련 메서드를 제공하는 클래스]
 */
@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;
    private final long refreshTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") long accessTokenExpTime,
            @Value("${jwt.refresh_expiration_time}") long refreshTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    /**
     * Access Token 생성
     * @param member
     * @return Access Token String
     */
    public JwtToken createJwtToken(MemberInfoDTO member) {
        return createJwtToken(member, accessTokenExpTime, refreshTokenExpTime);
    }


    /**
     * JWT 생성
     * @param member
     * @param expireTime
     * @return JWT Token
     */
    private JwtToken createJwtToken(MemberInfoDTO member, long expireTime, long refreshTokenExpTime) {
        Claims claims = Jwts.claims();
        claims.put("memberId", member.getMemberId());
        claims.put("email", member.getMemberEmail());
        claims.put("role", member.getMemberRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime accessTokenValidity = now.plusSeconds(expireTime);
        ZonedDateTime refreshTokenValidity = now.plusSeconds(refreshTokenExpTime);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(accessTokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(refreshTokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return new JwtToken(accessToken, refreshToken);
    }


    /**
     * Token에서 MemberInfo 추출
     * @param token
     * @return User ID
     */
    public MemberInfoDTO getMemberInfo(String token) {
        Claims claims = parseClaims(token);
        return new MemberInfoDTO(
                claims.get("memberId", String.class),
                claims.get("email", String.class),
                claims.get("role", String.class)
        );
    }


    /**
     * JWT 검증
     * @param token
     * @return IsValidate
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }


    /**
     * JWT Claims 추출
     * @param accessToken
     * @return JWT Claims
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }


}