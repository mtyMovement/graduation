package com.graduation.jaguar.core.common.util;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/17 0017
*/


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graduation.jaguar.core.dal.domain.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getUserId()))
                .sign(Algorithm.HMAC256(user.getUserPwd()));
        return token;
    }
}
