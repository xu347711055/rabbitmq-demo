package com.andy.rabbitmq.service;

import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class RabbitFanoutSender implements RabbitSender{

    private final static String LOG_INFO = "fanout模式：";

    @Autowired
    private RabbitTemplate rabbitTemplate;

/*    @Autowired
    private FanoutExchange fanoutExchange;*/

    private AtomicInteger id = new AtomicInteger(0);

    @Scheduled(cron = "${sender.publish.duration}")
    @Override
    public void send() {
        log.info(LOG_INFO + "开始发送订阅消息");
        Message message = new Message();
        message.setId(id.incrementAndGet());
        message.setContent("这是一条广播消息");
        message.setTimeStamp(String.valueOf(new Date().getTime()));
        rabbitTemplate.convertAndSend("test.fanout", "", message);
        log.info(LOG_INFO + "订阅消息发送完毕");
    }



}
