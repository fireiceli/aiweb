package com.aichat.aiweb.Controller;

import com.aichat.aiweb.Proc.Result;
import com.aichat.aiweb.Proc.User;

import com.aichat.aiweb.Service.UserService;
import com.aichat.aiweb.Utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginControl {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户{}登录",user.getUsername());
        User su = userService.login(user);
        if(su!=null){
            String jwt = JwtUtils.genAccessToken(su.getUsername(),su.getId());
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
