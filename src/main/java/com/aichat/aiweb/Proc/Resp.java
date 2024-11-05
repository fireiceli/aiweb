package com.aichat.aiweb.Proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp {
    private Long created;
    private HashMap<String,Object> usage;
    private ArrayList<HashMap<String,Object>> choices;
    private String model;
    private String id;
    private String object;
}
