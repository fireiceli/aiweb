package com.aichat.aiweb.Proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talk {
    private Integer id;
    private Integer userId;
    private String message;
    private String answer;
    private LocalDateTime timestamp;
    private Integer chatId;
}
