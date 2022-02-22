package com.example.demo.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.constant.Constants.REDIS_PROTOCOL_PREFIX;

@Configuration
public class CacheConfiguration {

    @Bean
    public RedissonClient redissonClient(@Value("${redis.host}") String host, @Value("${redis.port}") int port){

        Config config = new Config();
        config.useSingleServer().setAddress(String.format("%s%s:%d",REDIS_PROTOCOL_PREFIX,  host, port));
        return Redisson.create(config);
    }
}
