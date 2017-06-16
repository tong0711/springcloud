package com.landmaster.springboot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tdl on 2017/6/2.
 */
public class UserServiceImpl implements UserService {
    private Map<Long, User> userMap = new HashMap<Long, User>();
    public UserServiceImpl() {
        User user = new User();
        user.setUserId(10001L);
        user.setUsername("liyd1");
        user.setEmail("liyd1@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new User();
        user.setUserId(10002L);
        user.setUsername("liyd2");
        user.setEmail("liyd2@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new User();
        user.setUserId(10003L);
        user.setUsername("liyd3");
        user.setEmail("liyd3@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
    }
    @Override
    public String getName(Long userId) {
        return "liyd-" + userId;
    }
    @Override
    public User getUser(Long userId) {
        return userMap.get(userId);
    }
}
