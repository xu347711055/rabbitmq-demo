package com.andy.rabbitmq.receiver.config;

import com.andy.rabbitmq.common.CommonConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    static class FanoutExchangeConfig {

        @Bean
        public Queue fanoutQueue1() {
            return new Queue(CommonConstants.FANOUT_QUEUE);
        }

        @Bean
        public Queue fanoutQueue2() {
            return new Queue(CommonConstants.FANOUT_QUEUE);
        }

        @Bean
        public FanoutExchange fanout() {
            return new FanoutExchange("test.fanout");
        }

        @Bean
        public Binding bindingFanout1(FanoutExchange fanout,
                                      Queue fanoutQueue1) {
            return BindingBuilder.bind(fanoutQueue1).to(fanout);
        }

        @Bean
        public Binding bindingFanout2(FanoutExchange fanout,
                                      Queue fanoutQueue2) {
            return BindingBuilder.bind(fanoutQueue2).to(fanout);
        }

    }

    static class DirectExchangeConfig {

        @Bean
        public Queue directQueue1() {
            return new Queue(CommonConstants.DIRECT_QUEUE);
        }

        @Bean
        public Queue directQueue2() {
            return new Queue(CommonConstants.DIRECT_QUEUE);
        }

        @Bean
        public DirectExchange direct() {
            return new DirectExchange("test.direct");
        }


        @Bean
        public Binding bindingDirect1(DirectExchange direct,
                                      Queue directQueue1) {
            return BindingBuilder.bind(directQueue1).to(direct).with("color-white");
        }

        @Bean
        public Binding bindingDirect2(DirectExchange direct,
                                      Queue directQueue2) {
            return BindingBuilder.bind(directQueue2).to(direct).with("color-black");
        }
    }

    static class WorkQueueConfig {

        @Bean
        public Queue workQueue() {
            return new Queue(CommonConstants.WORK_QUEUE);
        }

    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

/*    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(MessageConverter jackson2JsonMessageConverter, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        //每个消费者可处理的未确认信息数量，设为1表示在消息服务器在收到消费者处理完毕应答后，队列才重新分配消息给消费者
        //该值越高，传递消息的速度就越快，但是非顺序处理的风险就越高。
        //TODO PrefetchCount 看看能否在application.yml里面配置
//        factory.setPrefetchCount(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        return factory;
    }*/

/*    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
        factory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);//缓存connection和channel
        factory.setConnectionNameStrategy(connectionNameStrategy());
        factory.setRequestedHeartBeat(30);
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }*/

/*
    @Bean
    public ConnectionNameStrategy connectionNameStrategy() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }
*/

}
