package com.KafkaProject.CabBooking.Configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.consumer.bootstrap-server}")
    public String bootstrapServer;

    @Bean
    public DefaultKafkaConsumerFactory kafkaConsumerFactory(){

        Map<String,Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        kafkaConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id-0");
        kafkaConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory(kafkaConfig);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory getKafkaListner(){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return factory;
    }

}
