package com.lee.interceptor;


import com.auth0.jwt.exceptions.*;
import com.lee.utils.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new Exception("token不能为空");
        }
        try {
            JwtUtils.verity(token);
        } catch (SignatureVerificationException e) {
            //log.error("无效签名！ 错误 ->", e);
            return false;
        } catch (TokenExpiredException e) {
            //log.error("token过期！ 错误 ->", e);
            return false;
        } catch (AlgorithmMismatchException e) {
            //log.error("token算法不一致！ 错误 ->", e);
            return false;
        } catch (Exception e) {
            //log.error("token无效！ 错误 ->", e);
            return false;
        }
        return true;
    }

}