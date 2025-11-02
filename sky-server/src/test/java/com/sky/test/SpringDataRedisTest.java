package com.sky.test;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unused")
    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations  hashOperations = redisTemplate.opsForHash();
        ListOperations  listOperations = redisTemplate.opsForList();
        ZSetOperations  zSetOperations = redisTemplate.opsForZSet();
        SetOperations   setOperations  = redisTemplate.opsForSet();
    }

    /**
     * 操作字符串类型数据
     */
    @Test
    public void testString() {
        redisTemplate.opsForValue().set("code", "1234");
        String city = (String)redisTemplate.opsForValue().get("city");
        System.out.println(city);

        redisTemplate.opsForValue().set("code", 1234, 3, TimeUnit.MINUTES);

        redisTemplate.opsForValue().setIfAbsent("lock", 1);
        redisTemplate.opsForValue().setIfAbsent("lock", 2);
    }
}