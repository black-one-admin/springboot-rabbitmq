package com.xkcoding.mq.rabbitmq.controller;

import com.xkcoding.mq.rabbitmq.response.BaseResponse;
import com.xkcoding.mq.rabbitmq.response.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主题控制层
 *
 * @author swh
 * @create: 2020-02-27 16:17
 */
@Controller
@Slf4j
public class TopicController {

    @Value("${mq.config.exchange.topic}")
    private String exchange;

    @Autowired
    private Environment env;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping(value ="/topic",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse topic(String message) throws Exception{

        message = "work机制生产消息"+System.currentTimeMillis();
        log.info("发送消息： {} ",message);
        rabbitTemplate.convertAndSend(this.exchange, "order.log.debug", "product...debug========"+message);
        rabbitTemplate.convertAndSend(this.exchange, "order.log.info", "order....info========"+message);
        rabbitTemplate.convertAndSend(this.exchange, "order.log.warn", "order...warn========"+message);
        rabbitTemplate.convertAndSend(this.exchange, "order.log.error", "order...error========"+message);
        return new BaseResponse(Status.Success);
    }
}
