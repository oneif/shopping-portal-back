package com.portal.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "oneif";
    // 过期时间 一个月
    private static final Long time = 1000L * 60 * 60 * 24 * 30;

    //接收业务数据,生成token并返回
    public static String getToken(Map<String, Object> claims) {

        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
