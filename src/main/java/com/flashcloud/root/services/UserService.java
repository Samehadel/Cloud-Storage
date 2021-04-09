package com.flashcloud.root.services;

import com.flashcloud.root.model.User;

public interface UserService {
    public int createUser(User user);
    public User getUser(String username);
    public int getUserId(String username);
}
