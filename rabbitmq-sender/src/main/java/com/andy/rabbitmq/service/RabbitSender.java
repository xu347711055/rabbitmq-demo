package com.andy.rabbitmq.service;

import com.andy.rabbitmq.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class RabbitSender {

    private final static String LOG_INFO = "发送者定时任务";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "${sender.duration}")
    public void send() {
        log.info(LOG_INFO + "开始发送消息");
        Date date = new Date();
        String message =  date.toString() + ": Hello World";
        rabbitTemplate.convertAndSend(CommonConstants.QUEUE_NAME, message);
        log.info(LOG_INFO + "消息发送完毕");
    }

}
