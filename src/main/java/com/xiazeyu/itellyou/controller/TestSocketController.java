package com.xiazeyu.itellyou.controller;

import com.xiazeyu.itellyou.service.TestSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/api")
public class TestSocketController {

    /**
     * 群发消息内容
     *
     * @param message
     * @return
     */
    @GetMapping(value = "/sendAll")
    public String sendAllMessage(String message) {
        TestSocketServer.sendAll(message);
        return "ok";
    }

}
