package com.andy.rabbitmq.configuration;

import com.andy.rabbitmq.common.CommonConstants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
/*
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("test.fanout");
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("test.direct");
    }*/

/*    @Bean
    public Queue queue() {
        return new Queue(CommonConstants.WORK_QUEUE);
    }*/

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
