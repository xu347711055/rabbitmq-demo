package com.andy.rabbitmq.service;

import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RabbitDirectSender implements RabbitSender {

/*    @Autowired
    private DirectExchange directExchange;*/

    private final static String[] routingKeys = {"color-blue", "color-black", "color-white"};

    private AtomicInteger colorIndex = new AtomicInteger(0);

    private AtomicInteger id = new AtomicInteger(0);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final static String LOG_INFO = "direct模式：";

    @Scheduled(cron = "${sender.publish.duration}")
    @Override
    public void send() {
        log.info(LOG_INFO + "开始发送订阅消息");
        if (colorIndex.get() == 3) {
            colorIndex.set(0);
        }
        String routingKey = routingKeys[colorIndex.getAndIncrement()];
        Message message = new Message();
        message.setId(id.incrementAndGet());
        message.setContent(routingKey);
        message.setTimeStamp(String.valueOf(new Date().getTime()));
        rabbitTemplate.convertAndSend("test.direct", routingKey, message);
        log.info(LOG_INFO + "订阅消息发送完毕");
    }
}
