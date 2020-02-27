package com.xkcoding.mq.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Work机制配置
 *
 * @author swh
 * @create: 2020-02-27 14:29
 */
@Component
public class WorkConfig {

    @Value("${mq.work.queue}")
    private String workQueue;

    /**
     * 注册work机制队列
     * @return
     */
    @Bean
    public Queue workQueue(){
        return new Queue(workQueue,true);
    }
}
