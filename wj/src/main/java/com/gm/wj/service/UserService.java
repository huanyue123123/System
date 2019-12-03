package com.gm.wj.service;

import com.gm.wj.dao.UserMapper;
import com.gm.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        return userMapper.selectUserByEntity(user);
    }
}
