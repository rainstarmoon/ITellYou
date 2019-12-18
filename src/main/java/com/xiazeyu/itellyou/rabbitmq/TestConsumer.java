package com.xiazeyu.itellyou.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener
public class TestConsumer {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @RabbitHandler
    public void receiveToConsole(String message) {
        System.out.println(message);
    }

    @RabbitHandler
    public void receive(String message) {
        System.out.println(message);
    }

}
