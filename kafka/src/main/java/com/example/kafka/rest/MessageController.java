package com.example.kafka.rest;

import com.example.kafka.model.BODto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, BODto> kafkaTemplate;

    public MessageController(KafkaTemplate<String, BODto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //    @PostMapping()
//    public void publish(@RequestBody MessageRequest messageRequest){
//        kafkaTemplate.send("business_owner_info", messageRequest.message());
//    }
    @PostMapping()
    public void publish(@RequestBody BODto boDto) {

        //String s = boDto.toString();
        //System.out.println(s);
        kafkaTemplate.send("business_owner_info", boDto);
    }

}

