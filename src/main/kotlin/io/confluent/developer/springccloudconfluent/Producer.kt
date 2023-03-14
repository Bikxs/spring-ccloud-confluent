package io.confluent.developer.springccloudconfluent

import com.github.javafaker.Faker
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import java.time.Duration
import java.util.function.Function
import java.util.stream.Stream

@Component
class Producer(
    val template: KafkaTemplate<Int, String>

) {
    val faker: Faker = Faker.instance()

    @EventListener(ApplicationStartedEvent::class)
    fun generate() {
        val interval: Flux<Long> = Flux.interval(Duration.ofMillis(1000))

        val quotes = Flux.fromStream(Stream.generate {
            faker.hobbit().quote()
        })


        Flux.zip(interval, quotes)
            .map(Function<Tuple2<Long?, String?>, Any> { it: Tuple2<Long?, String?> ->
                template.send(
                    "hobbit-avro",
                    faker.random().nextInt(42),
                    it.t2
                )
            }).blockLast()
    }

}