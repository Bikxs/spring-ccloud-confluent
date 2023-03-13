package io.confluent.developer.springccloudconfluent

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    @KafkaListener(topics = ["hobbit"], groupId = "spring-boot-kafka")
    fun consume(record: ConsumerRecord<Integer, String>) {
        println("received= ${record.value()}")
    }
}