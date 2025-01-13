package com.example.course5_9.comsumer;

import com.example.course5_9.bo.UserBo;
import com.example.course5_9.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaConfig.TEST_TOPIC, groupId = KafkaConfig.GROUP_1)
    public void consume(String message) {
        LOGGER.info("Consumed message: {}", message);
    }

    @KafkaListener(
            topics = KafkaConfig.JSON_TOPIC,
            groupId = KafkaConfig.GROUP_2,
            containerFactory = "userKafkaListenerFactory"
    )
    public void consumeJson(UserBo user) throws InterruptedException {
        LOGGER.info("Consumed JSON Message: {}", user);
    }
}
