package com.xkcoding.mq.rabbitmq.controller;

import com.xkcoding.mq.rabbitmq.response.BaseResponse;
import com.xkcoding.mq.rabbitmq.response.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * direct工作机制
 *
 * @author swh
 * @create: 2020-02-27 14:52
 */
@Controller
@Slf4j
public class DirectQueueController {

    @Autowired
    private Environment env;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * direct机制下，可以使用路由选择要发送的队列
     * @param message
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/direct",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse sendAndRec(String message) throws Exception{

        message = "work机制生产消息"+System.currentTimeMillis();
        try {

            rabbitTemplate.convertAndSend(env.getProperty("mq.direct.queue.exchange"),
                    env.getProperty("mq.direct.queue.routingEmail"),message);
            rabbitTemplate.convertAndSend(env.getProperty("mq.direct.queue.exchange"),
                    env.getProperty("mq.direct.queue.routingPhone"),message);

        }catch (Exception e){
            log.error("发送简单消息发生异常： ",e.fillInStackTrace());
        }
        log.info("发送消息： {} ",message);
        return new BaseResponse(Status.Success);
    }
}
