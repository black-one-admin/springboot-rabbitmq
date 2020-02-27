package com.xkcoding.mq.rabbitmq.customer;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 主题消费者
 *@RabbitListener
 * bindings：设置路由键绑定规则，进行队列消息的监听
 * @QueueBinding：中@Queue(value = "${mq.config.queue.info}"绑定队列名称,给该消费者取个队列名称
 *
 * autoDelete="false"这个属性的意思是如果接收方服务器发生异常，导致没接受到消息，将不会反馈ACK消息，
 * 那么，队列会把消息重新再放到队列里，来保证队列消息不会丢失。
 *
 *   @Exchange(value ="${mq.config.exchange}",type = ExchangeTypes.TOPIC),设置交换器名称和主题类型
 * key = "*.log.info"路由键的模糊匹配格式。
 * @author swh
 * @create: 2020-02-27 16:07
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "${mq.config.queue.info}" ,autoDelete="false"),
                exchange = @Exchange(value = "${mq.config.exchange.topic}",type = ExchangeTypes.TOPIC),
                key = "*.log.info"
        )
)
public class TopicQueue {

    @RabbitHandler
    public void receive(String msg){
        System.out.println("主题模式消费者Info receiver :" +msg);
    }
}
