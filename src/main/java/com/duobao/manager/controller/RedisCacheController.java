package com.duobao.manager.controller;

import com.duobao.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("redisCacheController")
public class RedisCacheController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("getAllKey")
    public String getAllKey(Map<String,Object> map) {
        List<String> allKey = redisUtil.getAllKey();
        map.put("msg", allKey);
        return "redisAllKey";
    }

    @RequestMapping("newPage")
    public String newPage() {
        return "redisCacheManager";
    }

    @RequestMapping("searchCache")
    public String searchCache() {
        return "searchCache";
    }

    @RequestMapping("searchCacheByKey")
    public String searchCacheByKey(Map<String,Object> map,String key) {
        String value = redisUtil.get(key);
        map.put("msg", value);
        map.put("key",key);
        return "searchCache";
    }
    @RequestMapping("setExpriseByKey")
    public String setExpriseByKey(Map<String,Object> map,String key1,int seconds) {
        Long value = redisUtil.setKeyExpire(key1,seconds);
        map.put("key1",key1);
        return "searchCache";
    }

    @RequestMapping("setAllKeyExprise")
    public String setAllKeyExprise(int days) {
        if (days < 2) {
            days=2;
        }
        redisUtil.setAllKeyExpire(days);
        return "searchCache";
    }
}
