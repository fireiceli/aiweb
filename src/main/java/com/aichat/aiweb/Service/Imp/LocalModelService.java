package com.aichat.aiweb.Service.Imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

//@Service
public class LocalModelService {

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    public String generateResponse(String input) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 假设Ollama接口需要的请求体结构如下
        String requestJson = "{\"prompt\": \"" + input + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        String response = restTemplate.postForObject(ollamaApiUrl, entity, String.class);
        return response;
    }
}
