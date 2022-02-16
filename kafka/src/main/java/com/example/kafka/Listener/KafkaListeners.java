package com.example.kafka.Listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "newSpringTopic",
            groupId = "groupId"
    )
    void listener(String data){
        System.out.println("Listener received: " +data);
    }
}
