package pro.notes.rabbitmq.publisher

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
@EnableRabbit
class Publisher (
    val rabbitTemplate: RabbitTemplate,
    val queue: Queue
){

    fun send(message: Any){
        rabbitTemplate.convertAndSend(queue.name, message)
    }
}