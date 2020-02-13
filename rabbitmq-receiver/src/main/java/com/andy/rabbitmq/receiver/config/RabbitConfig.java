package com.andy.rabbitmq.receiver.config;

import com.andy.rabbitmq.common.CommonConstants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue1() {
        return new Queue(CommonConstants.QUEUE_NAME1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(CommonConstants.QUEUE_NAME1);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        //每个消费者可处理的未确认信息数量，设为1表示在消息服务器在收到消费者处理完毕应答后，队列才重新分配消息给消费者
        //该值越高，传递消息的速度就越快，但是非顺序处理的风险就越高。
        //TODO PrefetchCount 看看能否在application.yml里面配置
        factory.setPrefetchCount(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

}
