package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer";

    public static final String SUPER_SECRET_KEY = "jkHJkhKJhkJH546351654jkghKJGkj543564356435KLhkjGKJg6546434kjgJKGkjg6464kjBVJKH";
    public static final long TOKEN_EXPIRATION_TIME = 1500000;

    public static Key getSignedKey(String secretKey){
        byte [] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
