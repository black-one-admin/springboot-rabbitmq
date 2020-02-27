package com.xkcoding.mq.rabbitmq.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * email消费者
 *
 * @author swh
 * @create: 2020-02-27 15:03
 */
@Component
@Slf4j
@RabbitListener(queues = "${mq.direct.queue.email}")
public class DirectQueueEmail {

    @Autowired
    @Qualifier("amqpDirectTemplate")
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieve(String msg) {
        log.info("email消费者接受消息:" + msg);
    }

}
