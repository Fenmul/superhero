package com.next.api.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // 定义交换机名称
    public static final String EXCHANGE_TOPIC_PUSH = "exchange_topic_push";

    // 定义队列名称
    public static final String QUEUE_NAME_PUSH = "queue_name_push";

    // 创建交换机
    @Bean(EXCHANGE_TOPIC_PUSH)
    public Exchange exchange () {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_PUSH)   // 定义交换机名称
                .durable(true) // 持久化
                .build();
    }

    @Bean(QUEUE_NAME_PUSH)
    public Queue queue () {
        return new Queue(QUEUE_NAME_PUSH);
    }

    @Bean
    public Binding pushQueueToExchange(
            @Qualifier(QUEUE_NAME_PUSH) Queue queue,
            @Qualifier(EXCHANGE_TOPIC_PUSH) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("push.*.*").noargs();
    }
}
