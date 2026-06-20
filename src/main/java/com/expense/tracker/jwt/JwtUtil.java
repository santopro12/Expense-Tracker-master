package com.expense.tracker.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private static final Key KEY =
            new SecretKeySpec(
                    SECRET.getBytes(),
                    SignatureAlgorithm.HS256.getJcaName());

    public static String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + 1000 * 60 * 60))
                .signWith(KEY)
                .compact();
    }
}
