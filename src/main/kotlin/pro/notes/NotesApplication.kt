package pro.notes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Pablo Roger
 */
@SpringBootApplication
class NotesApplication

/**
 * Notes is a notes manager app focused in RabbitMQ learning
 */

fun main(args: Array<String>) {
    runApplication<NotesApplication>(*args)
}
