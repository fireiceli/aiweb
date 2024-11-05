package com.aichat.aiweb.Service.Imp;

import com.aichat.aiweb.Mapper.ChatMapper;
import com.aichat.aiweb.Proc.Resp;
import com.aichat.aiweb.Proc.Talk;
import com.aichat.aiweb.Service.AIService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KimiAIService implements AIService {
    @Value("${kimi.api.url}")
    private String kimiApiUrl;

    @Value("${kimi.api.key}")
    private String kimiApiToken;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    public List<String> getHistoryChat(Talk talk){
        String key = "chat:"+talk.getUserId()+":"+talk.getChatId();
        List<String> chatList = redisTemplate.opsForList().range(key,-6,-1);
        if (chatList == null || chatList.isEmpty()) {
            // 如果缓存没有命中，从数据库中加载聊天记录
            List<Talk> talkList = chatMapper.getHisChat(talk);  // 假设数据库中有方法查找最近的3条聊天记录

            if (talkList != null && !talkList.isEmpty()) {
                // 将加载的聊天记录放入 Redis 并设置过期时间 10 分钟
                for(Talk each : talkList){
                    redisTemplate.opsForList().rightPush(key,each.getMessage());
                    redisTemplate.opsForList().rightPush(key,each.getAnswer());
                }
                redisTemplate.expire(key, 10, TimeUnit.MINUTES);
                return redisTemplate.opsForList().range(key,-6,-1);
            }
            else return new ArrayList<String>();
        }
        else return chatList;
    }

    public void saveNewChat(Talk talk){
        String key = "chat:"+talk.getUserId()+":"+talk.getChatId();
        chatMapper.insertChat(talk);
        redisTemplate.opsForList().rightPush(key,talk.getMessage());
        redisTemplate.opsForList().rightPush(key,talk.getAnswer());
        redisTemplate.opsForList().trim(key,-6,-1);
        redisTemplate.expire(key,10,TimeUnit.MINUTES);
    }

    public String generateResponse(Talk talk) {
        List<Object> messages = new ArrayList<>();
        List<String> historyChat=getHistoryChat(talk);
        for(int i=0;i<historyChat.toArray().length;i++){
            Map<String,String> hm = new HashMap<>();
            if(i%2==0) hm.put("role", "user");
            else hm.put("role", "assistant");
            hm.put("content", historyChat.get(i));
            messages.add(hm);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + kimiApiToken);

        String input = talk.getMessage();
        log.info("input is: {}", input);
        Map<String,String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", input);
        log.info("message is {}",message);
        messages.add(message);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "moonshot-v1-8k");
        request.put("messages", messages);
        request.put("temperature", 0.3);

        JSONObject requestbody = new JSONObject(request);

        log.info("Sending request to Kimi API: {}", kimiApiUrl);
        log.info("Request Headers: {}", headers.toString());
        log.info("Request Body: {}", requestbody.toString());


        HttpEntity<String> entity = new HttpEntity<>(requestbody.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                kimiApiUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        log.info("Response Body: {}", response.getBody());

        Resp re = JSONObject.parseObject(response.getBody(),Resp.class);
        Map<String,String> resmap=(Map<String, String>) re.getChoices().get(0).get("message");
        talk.setAnswer(resmap.get("content"));
        saveNewChat(talk);

        return resmap.get("content");
    }
}
