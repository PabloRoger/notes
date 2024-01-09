package pro.notes.rabbitmq.publisher

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PublisherConfig {

    @Value("\${notes.queue.name}")
    private lateinit var message: String

    @Bean
    fun queue(): Queue {
        return Queue(message, true)
    }
}