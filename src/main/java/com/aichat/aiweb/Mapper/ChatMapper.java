package com.aichat.aiweb.Mapper;

import com.aichat.aiweb.Proc.Talk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<Talk> getHisChat(Talk talk);
    void insertChat(Talk talk);
}
