package com.aichat.aiweb.Controller;

import com.aichat.aiweb.Proc.Result;
import com.aichat.aiweb.Proc.Talk;
import com.aichat.aiweb.Service.Imp.KimiAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModel {
//    @Autowired
//    private LocalModelService localModelService;

    @Autowired
    private KimiAIService kimiService;

    // 调用本地部署模型
//    @PostMapping("/local")
//    public String chatWithLocalModel(String input) {
//        return localModelService.generateResponse(input);
//    }

    // 调用OpenAI模型
    @PostMapping("/kimi-api")
    public Result chatWithOpenAI(@RequestBody Talk talk) {
        return Result.success(kimiService.generateResponse(talk));
    }
}

