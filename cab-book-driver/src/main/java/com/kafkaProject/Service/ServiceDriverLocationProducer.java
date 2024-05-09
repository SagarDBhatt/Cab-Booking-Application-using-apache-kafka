package com.kafkaProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceDriverLocationProducer {

    @Autowired
    public KafkaTemplate kafkaTemplate;


    public void sendLocEveryOneSecond(String data){
        kafkaTemplate.send("driver-location-every-one-second", data);
    }
}
