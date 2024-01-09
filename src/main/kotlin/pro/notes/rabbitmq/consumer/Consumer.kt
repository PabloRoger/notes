package pro.notes.rabbitmq.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import pro.notes.rabbitmq.Data

@Component
@Configuration
class Consumer {

    @RabbitListener(queues = ["\${notes.queue.name}"])
    fun receive(@Payload message: Data) {
        println("Received message $message")
    }

    @Bean
    fun rabbitListenerContainerFactory(connectionFactory: ConnectionFactory): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMessageConverter(messageConverter())
        return factory
    }

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

}