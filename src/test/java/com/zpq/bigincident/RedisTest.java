package com.zpq.bigincident;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedis;

    @Test
    public void setTest() {
        ValueOperations<String, String> operations = stringRedis.opsForValue();
        operations.set("username","张先生");
    }
    @Test
    public void getTest() {
        ValueOperations<String, String> operations = stringRedis.opsForValue();
        String username = operations.get("username");
        System.out.println(username);
    }
}
