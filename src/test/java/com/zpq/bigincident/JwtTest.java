package com.zpq.bigincident;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("user", "admin");
        // 生成jwt代码
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 添加过期时间
                .sign(Algorithm.HMAC256("zpq")); // 指定算法, 配置秘钥
        System.out.println(token);
    }

    @Test
    public void testParse() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE3MDI0OTg1MzIsInVzZXIiOnsiaWQiOjEsInVzZXIiOiJhZG1pbiJ9fQ" +
                ".CxShQO9U3TLYX1jSg4bS1I0A2FKjU7hwh89DzLci2XM";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("zpq")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        // 如果篡改头部或载荷部分的数据,验证失败
        // 秘钥修改,验证失败
        // token过期
    }
}
