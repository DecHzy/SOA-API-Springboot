package org.csu.soa.controller;

import org.csu.soa.service.chatgpt.ChatGPT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatGPTController {
    @PostMapping("/")
    public String getChatGPT(@RequestParam(required = true) String str) throws Exception {
        try {
            String returnStr = ChatGPT.chat(str);
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            // 服务异常
            return "";
        }
    }
}
