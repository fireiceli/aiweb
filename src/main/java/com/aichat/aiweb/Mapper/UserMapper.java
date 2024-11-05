package com.aichat.aiweb.Mapper;

import com.aichat.aiweb.Proc.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list();

    @Select("select * from users where username=#{username} and password=#{password}")
    User getloginuser(User user);
}
