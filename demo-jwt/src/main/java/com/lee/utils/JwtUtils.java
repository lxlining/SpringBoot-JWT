package com.lee.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lee.Entity.User;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JwtUtils {
/*
    *//**
     * 获取token
     * @param u user
     * @return token
     *//*
    public static String getToken(User u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间1分钟
        instance.add(Calendar.MINUTE,1);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getUserid())
                .withClaim("username", u.getUsername());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }

    *//**
     * 验证token合法性 成功返回token
     *//*
    public static DecodedJWT verify(String token) throws Exception {
    System.out.println(token);
        if(StringUtils.isEmpty(token)){
            throw new Exception("token不能为空");
        }

        //获取登录用户真正的密码假如数据库查出来的是123456
        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }*/


    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 3*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username,String userid){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("username",username)
                .withClaim("userid",userid).withExpiresAt(date).sign(algorithm);
    }


    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

    }
    public static User getInfo(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        DecodedJWT verifier = JWT.decode(token);
    System.out.println(verifier.getClaims());
    System.out.println(verifier);
    System.out.println(verifier.getClaim("username"));
    System.out.println(verifier.getPayload());
    User user=new User(verifier.getClaim("userid").asInt(),verifier.getClaim("username").asString(),null,token);
    return user;
    }
}