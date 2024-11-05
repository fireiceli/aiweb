package com.aichat.aiweb.Service;

import com.aichat.aiweb.Proc.User;

import java.util.List;

public interface UserService {
    public List<User> list();
    public User login(User user);
}
