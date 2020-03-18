package com.next.api.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    // 设置监听消息队
    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME_PUSH)
    public void getMessage(String payLoad, Message msg){
        System.out.println(payLoad);
    }
}
