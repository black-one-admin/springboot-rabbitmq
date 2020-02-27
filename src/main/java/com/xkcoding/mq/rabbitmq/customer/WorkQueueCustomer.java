package com.xkcoding.mq.rabbitmq.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * work工作机制消费者
 *
 * @author swh
 * @create: 2020-02-27 13:43
 */
@Component
@Slf4j
@RabbitListener(queues = "${mq.work.queue}")
public class WorkQueueCustomer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitHandler
    public void receive(String message){
        log.info("mq消费者接受消息： {} ",message);
    }

}
