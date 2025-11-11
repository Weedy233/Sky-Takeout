package com.sky.test;

import java.util.List;
import java.util.Set;
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
    private RedisTemplate<String, Object> redisTemplate;

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


    @Test
    public void testHash() {
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("100", "name", "weedy");
        hashOperations.put("100", "age", "24");

        hashOperations.put("100", "中文名", "温蒂");

        String name = (String) hashOperations.get("100", "name");
        System.out.println(name);

        Set keys = hashOperations.keys("100");
        System.out.println(keys);

        List values = hashOperations.values("100");
        System.out.println(values);

        hashOperations.delete("100", "age");
    }


    @Test
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();

        listOperations.leftPushAll("MyGo!!!!!", "tomori", "anon", "taki", "soyo", "rana");
        listOperations.leftPush("MyGO!!!!!", "macha pafue");

        List range = listOperations.range("MyGO!!!!!", 0, -1);
        System.out.println(range);
        
        listOperations.leftPop("MyGO!!!!!");

        Long size = listOperations.size("mylist");
        System.out.println(size);
    }

    @Test
    public void testSet() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();

        setOperations.add("set1", "a", "b", "c", "d");
        setOperations.add("set2", "a", "b", "x", "y");

        Set members = setOperations.members("set1");
        System.out.println(members);

        Long size = setOperations.size("set1");
        System.out.println(size);

        Set intersect = setOperations.intersect("set1", "set2");
        System.out.println(intersect);

        Set union = setOperations.union("set1", "set2");
        System.out.println(union);

        setOperations.remove("set1", "a", "b");
    }
}