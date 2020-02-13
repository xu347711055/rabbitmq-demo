package com.andy.rabbitmq.service;

import com.andy.rabbitmq.common.CommonConstants;
import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class RabbitSender {

    private final static String LOG_INFO = "发送者定时任务";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private AtomicInteger id = new AtomicInteger(0);

    @Scheduled(cron = "${sender.workqueues.duration}")
    public void send() {
        log.info(LOG_INFO + "开始发送队列消息");
        Message message = new Message();
        message.setId(id.incrementAndGet());
        message.setContent("Hello World");
        message.setTimeStamp(String.valueOf(new Date().getTime()));
        rabbitTemplate.convertAndSend(CommonConstants.QUEUE_NAME1, message);
        log.info(LOG_INFO + "队列消息发送完毕");
    }

    @Scheduled(cron = "${sender.publish.duration}")
    public void sendPublish() {
        log.info(LOG_INFO + "开始发送订阅消息");

        log.info(LOG_INFO + "订阅消息发送完毕");
    }

}
