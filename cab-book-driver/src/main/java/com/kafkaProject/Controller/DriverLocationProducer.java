package com.kafkaProject.Controller;

import com.kafkaProject.Configuration.KafkaConfiguration;
import com.kafkaProject.Service.ServiceDriverLocationProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/driverLocation")
public class DriverLocationProducer {

    @Autowired
    public KafkaTemplate kafkaTemplate;

    @Autowired
    public ServiceDriverLocationProducer serviceDriverLocationProducer;

    @PostMapping("/")
    public ResponseEntity<?> sendDriverLocationToKafkaTopic(){
        kafkaTemplate.send("DriverLocationTopicV2","Test Message");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("location","Location Send"));
    }

    /**
     * Create a API endpoint that sends the data to topic "updatedDriverLocation" every 1 second.
     */
    @PutMapping("/updateEveryOneSecond")
    public ResponseEntity<?> sendUpdatedDrtiverLocationEvery1Sec() throws InterruptedException {
        int counter = 100;
        while (counter > 0){

            //Service layer method to create the topic and send the data to the kafka topic.
            serviceDriverLocationProducer.sendLocEveryOneSecond(Math.random() + " : " + Math.random());
            Thread.sleep(1500);

            counter--;
        }

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Updated driver location data is send"));
    }
}
