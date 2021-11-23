package com.yhy.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("producer")
public class ProducerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("send_product")
    public String test() throws JsonProcessingException {
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setId(1L);
        kafkaMessage.setServiceId("productInfoService");
        ObjectMapper mapper = new ObjectMapper();
        String kafkaJsonMessage  = mapper.writeValueAsString(kafkaMessage);
        kafkaTemplate.send("testPartition",kafkaJsonMessage);
        String res = "消息:【" + kafkaJsonMessage + "】发送成功 SUCCESS !";
        logger.info(res);
        return res;
    }
}
