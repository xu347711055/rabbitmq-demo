package com.andy.rabbitmq.receiver.service;


import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitFanoutReceiver implements Receiver{

    @RabbitHandler
    @RabbitListener(queues = "#{fanoutQueue1.name}")
    @Override
    public void receiver1(Message message) {
        log.info("fanout消费者1接收到message=[{}]", message);

    }

    @RabbitHandler
    @RabbitListener(queues = "#{fanoutQueue2.name}")
    @Override
    public void receiver2(Message message) {
        log.info("fanout消费者2接收到message=[{}]", message);

    }
}
