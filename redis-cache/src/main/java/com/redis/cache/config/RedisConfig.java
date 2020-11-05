package com.redis.cache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @ClassName: RedisConfig
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/4
 * @Version: V1.0
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(JedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;

    }

    @Bean(name = "redisConnectionFactory")
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("123.56.143.183");
        redisStandaloneConfiguration.setPassword("redis123");
        redisStandaloneConfiguration.setDatabase(1);

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public Redisson redisson() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://123.56.143.183:6379").setPassword("redis123").setDatabase(1);
        RedissonClient redisson = Redisson.create(config);
        return (Redisson) redisson;
    }

}
