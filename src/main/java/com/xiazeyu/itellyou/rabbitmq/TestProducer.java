package com.xiazeyu.itellyou.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendMessage() {
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend("string", "");
    }

}
