package com.andy.rabbitmq.receiver.service;

import com.andy.rabbitmq.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = CommonConstants.QUEUE_NAME)
@Component
@Slf4j
public class RabbitReceiver {

    @RabbitHandler
    public void receive(String message) {
        log.info("receive message: [{}]", message);
    }

}
