package com.aichat.aiweb;


import com.aichat.aiweb.Mapper.ChatMapper;
import com.aichat.aiweb.Proc.Talk;
import com.aichat.aiweb.Proc.User;
import com.aichat.aiweb.Service.Imp.UserServiceimp;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@SpringBootTest
class AiwebApplicationTests {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Autowired
	private ChatMapper chatMapper;

	@Test
	void contextLoads() {
		Talk t=new Talk();
		t.setAnswer("sjj");
		t.setChatId(2);
		t.setUserId(1);
		t.setId(4);
		t.setMessage("adjj");
		chatMapper.insertChat(t);
		System.out.println(chatMapper.getHisChat(t).toString());
		redisTemplate.opsForList().rightPush("aop","adkao");
		redisTemplate.opsForList().rightPush("aop","adfdo");
		redisTemplate.expire("aop", 1, TimeUnit.MINUTES);
		List<String> l2 = redisTemplate.opsForList().range("aop",-6,-1);
		System.out.println(l2);
	}

}
