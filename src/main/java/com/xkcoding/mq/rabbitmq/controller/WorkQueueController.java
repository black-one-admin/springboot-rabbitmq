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
 * work工作机制
 *
 * @author swh
 * @create: 2020-02-27 13:21
 */
@Controller
@Slf4j
public class WorkQueueController {

    @Autowired
    private Environment env;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping(value ="/work",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse sendAndRec(String message) throws Exception{

        message = "work机制生产消息"+System.currentTimeMillis();
        log.info("发送消息： {} ",message);

        //第一个参数为：queue名，第二个参数为：message，在这里为一个String：这是mq的第一条消息
        rabbitTemplate.convertAndSend(env.getProperty("mq.work.queue"),message);
        return new BaseResponse(Status.Success);
    }


}
