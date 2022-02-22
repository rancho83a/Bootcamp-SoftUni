package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.service.JWTService;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.example.demo.constant.Constants.TOKEN_PREFIX;


@Service
public class JWTServiceImpl implements JWTService {
    private final RMapCache<String, DecodedJWT> validToken;
    private final RMapCache<UserLoginRequestDto,String> activeTokens;
    private final RSetCache<String> blockList;

    private final String secret;

    public JWTServiceImpl(RedissonClient redissonClient,  @Value("${secret.value}") String secret) {
        this.secret = secret;
        this.validToken = redissonClient.getMapCache("valid_tokens");
        this.activeTokens = redissonClient.getMapCache("active_tokens");
        this.blockList = redissonClient.getSetCache("block_tokens");
    }

    private Optional<DecodedJWT> decodeJWT(String token) {

        try{

            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token);

            this.validToken.put(
                            token,
                    decodedJWT, decodedJWT.getExpiresAt().getTime() - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS
            );

            return Optional.of(decodedJWT);

        }catch(Exception e){
            this.blockList.add(token);

            return Optional.empty();

        }
    }

    @Override
    public Optional<DecodedJWT> getDecodedJWT(String token) {
        Optional<DecodedJWT> cacheDecodedJWT = this.getCachedDecodeJWT(token);
        return cacheDecodedJWT.isEmpty() ? this.decodeJWT(token) : cacheDecodedJWT;
    }

    private Optional<DecodedJWT> getCachedDecodeJWT(String token) {
        return Optional.ofNullable(this.validToken.get(token));
    }

    @Override
    public boolean isInBlackList(String token) {
        return  this.blockList.contains(token);
    }

    @Override
    public void cacheIssuedHeaderToken(UserLoginRequestDto userLoginRequestDto, String headerToken) {
        Optional<DecodedJWT> decodedJWT = this.getDecodedJWT(headerToken.replace(TOKEN_PREFIX, ""));
        long expirationTime = decodedJWT.get().getExpiresAt().getTime() - System.currentTimeMillis();

        this.activeTokens.put(userLoginRequestDto, headerToken, expirationTime, TimeUnit.MILLISECONDS);

    }

    @Override
    public Optional<String> getCachedHeaderToken(UserLoginRequestDto userLoginRequestDto) {

        return Optional.ofNullable(this.activeTokens.get(userLoginRequestDto));
    }


}
