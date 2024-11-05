package com.aichat.aiweb.Controller;

import com.aichat.aiweb.Proc.Result;
import com.aichat.aiweb.Proc.User;
import com.aichat.aiweb.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserControl {
    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public Result list(){
        List<User> users= userService.list();
        return Result.success(users);
    }
}
