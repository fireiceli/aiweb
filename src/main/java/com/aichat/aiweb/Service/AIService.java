package com.aichat.aiweb.Service;

import com.aichat.aiweb.Proc.Talk;

import java.util.ArrayList;
import java.util.List;

public interface AIService {
    public List<String> getHistoryChat(Talk talk);
    public void saveNewChat(Talk talk);
    public String generateResponse(Talk talk);
}
