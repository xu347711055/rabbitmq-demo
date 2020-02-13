package com.andy.rabbitmq.receiver.service;

import com.andy.rabbitmq.common.CommonConstants;
import com.andy.rabbitmq.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class RabbitReceiver {

    @Value("${receiver.queue1.wait}")
    private Integer queue1WaitTime;
    @Value("${receiver.queue2.wait}")
    private Integer queue2WaitTime;

    @RabbitHandler
    @RabbitListener(queues = CommonConstants.QUEUE_NAME1)
    public void receive1(Message message) {
        StopWatch watch = new StopWatch();
        watch.start();
        if (message == null) {
            return;
        }
        //奇数延迟
        if (message.getId()%2 != 0) {
            try {
                Thread.sleep(queue1WaitTime);
            } catch (InterruptedException e) {
                log.error("sleep出现异常", e);
            }
        }
        watch.stop();
        log.info("队列1接收到message: [{}], 处理时间为: {}s", message, watch.getTotalTimeSeconds());
    }

    @RabbitHandler
    @RabbitListener(queues = CommonConstants.QUEUE_NAME1)
    public void receive2(Message message) {
        StopWatch watch = new StopWatch();
        watch.start();
        if (message == null) {
            return;
        }
        //奇数延迟
        if (message.getId()%2 != 0) {
            try {
                Thread.sleep(queue2WaitTime);
            } catch (InterruptedException e) {
                log.error("sleep出现异常", e);
            }
        }
        watch.stop();
        log.info("队列2接收到message: [{}], 处理时间为: {}s", message, watch.getTotalTimeSeconds());
    }
}
