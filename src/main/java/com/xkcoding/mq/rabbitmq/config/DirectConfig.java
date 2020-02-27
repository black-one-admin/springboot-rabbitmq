package com.xkcoding.mq.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Direct机制配置
 *
 * @author swh
 * @create: 2020-02-27 14:27
 */
@Component
public class DirectConfig {
    @Value("${mq.direct.queue.exchange}")
    private String directExchangeName;

    @Value("${mq.direct.queue.routingEmail}")
    private String directRounterEmail;

    @Value("${mq.direct.queue.routingPhone}")
    private String directRounterPhone;

    @Value("${mq.direct.queue.email}")
    private String emailQueue;
    @Value("${mq.direct.queue.phone}")
    private String phoneQueue;

    /**
     * 注册交换机
     * @return
     */
    @Bean
    public DirectExchange receiptExchange(){
        return new DirectExchange(directExchangeName,true,false);
    }

    /**
     * 注册emailQueue队列
     * @return
     */
    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue,true);
    }

    /**
     * 注册phoneQueue队列
     * @return
     */
    @Bean
    public Queue phoneQueue(){
        return new Queue(phoneQueue,true);
    }

    /**
     * 将email队列绑定到directExchangeName交换机，并设置email路由
     * @return
     */
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(receiptExchange()).with(directRounterEmail);
    }

    /**
     * 将phone队列绑定到directExchangeName交换机，并设置phone路由
     * @return
     */
    @Bean
    public Binding phoneBinding(){
        return BindingBuilder.bind(phoneQueue()).to(receiptExchange()).with(directRounterPhone);
    }
}
