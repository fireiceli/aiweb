package com.aichat.aiweb.Service.Imp;

import com.aichat.aiweb.Mapper.UserMapper;
import com.aichat.aiweb.Proc.User;
import com.aichat.aiweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimp implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Cacheable(value="users",key="'all'")
    public List<User> list(){
        return usermapper.list();
    }

    public User login(User user){
       return usermapper.getloginuser(user);
    }
}
