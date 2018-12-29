package com.lyloou.demo.controller;

import com.lyloou.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RedisController {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate = null;


    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;


    @GetMapping("/redis/value/{key}/{value}")
    @ResponseBody
    public Map<String, String> testValue(@PathVariable("key") String key,
                                         @PathVariable("value") String value) {

        redisTemplate.boundValueOps(key).set(value);
        Map<String, String> map = new HashMap<>();

        String redisValue = (String) redisTemplate.opsForValue().get(key);
        map.put(key, redisValue);
        return map;
    }


    @GetMapping("/redis/hash/{field1}/{value1}/{field2}/{value2}")
    @ResponseBody
    public Map<String, String> testHash(
            @PathVariable("field1") String field1,
            @PathVariable("value1") String value1,
            @PathVariable("field2") String field2,
            @PathVariable("value2") String value2) {
        String key = "hash";
        Map<String, String> map = new HashMap<>();
        map.put(field1, value1);
        map.put(field2, value2);

        redisTemplate.boundHashOps(key).putAll(map);
        String redisValue = (String) redisTemplate.boundHashOps(key).get(field1);
        System.out.println(redisValue);
        return map;
    }


    @GetMapping("/string/redis/value/{key}/{value}")
    @ResponseBody
    public Map<String, String> testString(@PathVariable("key") String key,
                                          @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        Map<String, String> map = new HashMap<>();

        String redisValue = stringRedisTemplate.boundValueOps(key).get();
        map.put(key, redisValue);
        return map;
    }

    @GetMapping("/redis/role/{id}/{roleName}/{note}")
    @ResponseBody
    public Role testRole(@PathVariable("id") Long id,
                         @PathVariable("roleName") String roleName,
                         @PathVariable("note") String note) {
        SessionCallback<Role> callback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations operations) throws DataAccessException {
                Role role = new Role();
                role.setId(id);
                role.setRoleName(roleName);
                role.setNote(note);
                String key = "role";

                operations.opsForValue().set(key, role);

                return (Role) operations.opsForValue().get(key);
            }
        };

        return redisTemplate.execute(callback);
    }


}