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
public class RabbitWorkQueueReceiver implements Receiver{

    @Value("${receiver.queue1.wait}")
    private Integer queue1WaitTime;
    @Value("${receiver.queue2.wait}")
    private Integer queue2WaitTime;

    @RabbitHandler
    @RabbitListener(queues = CommonConstants.WORK_QUEUE)
    @Override
    public void receiver1(Message message) {
        process(message, 1);
    }

    @RabbitHandler
    @RabbitListener(queues = CommonConstants.WORK_QUEUE)
    @Override
    public void receiver2(Message message) {
        process(message, 2);
    }

    public void process(Message message, int queueId) {
        StopWatch watch = new StopWatch();
        watch.start();
        if (message == null) {
            return;
        }
        //奇数延迟
        if (message.getId()%2 != 0) {
            int waitTime = queueId == 1 ? queue1WaitTime : queue2WaitTime;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                log.error("sleep出现异常", e);
            }
        }
        watch.stop();
        log.info("点对点消费者{}接收到message: [{}], 处理时间为: {}s", queueId, message, watch.getTotalTimeSeconds());
    }
}
