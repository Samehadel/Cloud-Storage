package com.flashcloud.root.services.impl;

import com.flashcloud.root.mapper.UserMapper;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import com.flashcloud.root.utils.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EncryptionService encryptionService;


    @Override
    public int createUser(User user) {
        String salt = encryptionService.generateSalt();
        String encryptedPassword = encryptionService.encryptValue(user.getPassword(), salt);

        user.setSalt(salt);
        user.setPassword(encryptedPassword);

        return userMapper.insert(user);
    }

    @Override
    public User getUser(String username) {
        return userMapper.find(username);
    }

    @Override
    public int getUserId(String username) {
        return userMapper.find(username).getUserId();
    }
}
