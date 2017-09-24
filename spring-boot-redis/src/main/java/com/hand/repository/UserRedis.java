package com.hand.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hand.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinliang on 2017/9/24.
 * UserRedis repository
 */
@Repository
public class UserRedis {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //添加单个用户
    public void add(String key, Long time, User user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(user),time, TimeUnit.MINUTES);
    }

    //添加多个用户
    public void add(String key, Long time, List<User> users){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(users),time,TimeUnit.MINUTES);
    }

    //获取单个用户
    public User get(String key){
        Gson gson = new Gson();
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(userJson)){
            user = gson.fromJson(userJson, User.class);
        }
        return user;
    }

    //获取多个用户
    public List<User> getList(String key){
        Gson gson = new Gson();
        List<User> userList=null;
        String usersJson = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(usersJson)){
            userList = gson.fromJson(usersJson,new TypeToken<List<User>>(){}.getType());
        }
        return userList;
    }

    //删除单个用户
    public void  delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);

    }


}
