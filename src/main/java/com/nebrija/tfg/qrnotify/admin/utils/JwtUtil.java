package com.nebrija.tfg.qrnotify.admin.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "g97q3IABYfbIbUo/BaTkBEXdgm6QBiMRHFWbmRKS+jY="; // Asegúrate de que el secreto tenga al menos 256 bits

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String parseToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return jws.getBody().getSubject();
        } catch (JwtException e) {
            // El token es inválido
            return null;
        }
    }

    public static String generateToken(String username, String phoneNumber) {
        return Jwts.builder().setSubject(username).claim("phoneNumber", phoneNumber).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hora de duración del token
                .signWith(key).compact();
    }
}