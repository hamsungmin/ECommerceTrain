package com.ecommerce.config;

@RedisConfig
public class RedisConfig {
    private final RedisProperties redisProperties;

    public RedisConnectionFactory redisConnectionFactory(redisProperties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return new LettuceConnectionFactory(config);
    }
}
