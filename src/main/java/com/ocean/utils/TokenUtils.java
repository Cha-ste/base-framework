package com.ocean.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token工具类
 */
public class TokenUtils {
    private static final long EXPIRED_TIMES = 1000*60*30; //token有效时间为30分钟
    private static final int EXPIRED_SECOND = 60*30; //token有效时间为30分钟
    private static final String  SECRET = "ocean_secret";

    //发布者 后面一块去校验
    private static final String  ISSUER = "mooc_user";

    /**
     * 生成token的操作
     */
    public static String genToken(){
        Map<String, String> claims  = new HashMap<>();
        long startTime = System.currentTimeMillis();
        long expiredTime = startTime + EXPIRED_TIMES;
        claims.put("startTime", String.valueOf(startTime));
        claims.put("expiredTime", String.valueOf(expiredTime));

        try {
            //签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER)
                    .withExpiresAt(DateUtils.addDays(new Date(), 1));
            //相当于将claims存储在token中
            claims.forEach((k,v) -> builder.withClaim(k, v));
            String token = builder.sign(algorithm).toString();

//            JedisUtils.setListExpired("token", EXPIRED_SECOND, token);
            JedisUtils.setList("token", token);

            return token;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取token中的 playLoad
     *
     * @param token
     * @return
     */
    public static Map<String, String> verifyToken(String token)  {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt =  verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<>();
        map.forEach((k,v) -> resultMap.put(k, v.asString()));

        return resultMap;
    }

    /**
     * 校验token是否过期
     *
     * @param token
     * @return
     */
    public static boolean validate(String token) {
        if (token == null || "".equals(token)) {
            return false;
        }

        Long count = JedisUtils.delList("token", token);
        if (count < 1) {
            return false;
        }

        Map<String, String> claim = verifyToken(token);
        long startTime = Long.parseLong(claim.get("startTime"));
        long expiredTime = Long.parseLong(claim.get("expiredTime"));

        if (startTime > System.currentTimeMillis()
                || expiredTime < System.currentTimeMillis()) {
            return false;
        }

        return true;
    }

}
