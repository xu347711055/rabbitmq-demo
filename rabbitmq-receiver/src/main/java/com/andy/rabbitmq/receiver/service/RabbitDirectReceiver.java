package com.andy.rabbitmq.receiver.service;


import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class RabbitDirectReceiver implements Receiver {

    @RabbitHandler
    @RabbitListener(queues = "#{directQueue1.name}")
    @Override
    public void receiver1(Message message) {
        log.info("direct消费者1接收到message=[{}]", message);
    }

    @RabbitHandler
    @RabbitListener(queues = "#{directQueue2.name}")
    @Override
    public void receiver2(Message message) {
        log.info("direct消费者2接收到message=[{}]", message);
    }

}
