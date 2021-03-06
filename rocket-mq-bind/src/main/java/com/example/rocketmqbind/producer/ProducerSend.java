package com.example.rocketmqbind.producer;

import com.example.rocketmqbind.bind.CustomBinding;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2020/7/6 0:56
 * @Created by 15617
 */
@Slf4j
@Component
public class ProducerSend {

    @Autowired
    @Qualifier(CustomBinding.outputPro)
    private MessageChannel messageChannel;

    public void send(String content) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(RocketMQHeaders.TAGS, "strTag");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(content, messageHeaders);
        this.messageChannel.send(message);
        log.info("send ok..........");
    }

    public void sendDelay(String content) {
        MessageBuilder<String> builder = MessageBuilder.withPayload(content)
                .setHeader(RocketMQHeaders.TAGS, "strTagDelay")
                .setHeader("DELAY", "3");
        Message<String> message = builder.build();
        messageChannel.send(message);
        log.info("sendDelay ok..........");
    }
}
