package io.confluent.developer.springccloudconfluent

import io.confluent.developer.avro.Hobbit
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    @KafkaListener(topics = ["hobbit-avro"], groupId = "spring-boot-kafka")
    fun consume(record: ConsumerRecord<Integer, Hobbit>) {
        println("received= ${record.value()}")
    }
}