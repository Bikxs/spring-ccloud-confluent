package io.confluent.developer.springccloudconfluent.config

import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.*
import java.util.Map

@Configuration
class KafkaConfiguration {

    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(
            Map.of<String, Any>(
                CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                RETRIES_CONFIG, 0,
                BUFFER_MEMORY_CONFIG, 33554432,
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java,
                VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java
            )
        )
    }


    fun consumerFactory(): ConsumerFactory<String, String> {
        val consumerProperties = Map.of<String, Any>(
            CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
            GROUP_ID_CONFIG, "spring-ccloud",
            ENABLE_AUTO_COMMIT_CONFIG, false,
            SESSION_TIMEOUT_MS_CONFIG, 15000,
            KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java,
            VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java
        )

        return DefaultKafkaConsumerFactory(consumerProperties)
    }


    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory)
    }

    @Bean
    fun hobbit2(): NewTopic {
        return TopicBuilder.name("hobbit2").partitions(12).replicas(3).build()
    }


}