package com.KafkaProject.CabBooking;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerClass {

    @KafkaListener(topics = "driver-location-every-one-second", groupId = "group-id-0")
    public void kafkaTopicConsumer(String message){
        System.out.println("Mesage received : "  + message);
    }
}
