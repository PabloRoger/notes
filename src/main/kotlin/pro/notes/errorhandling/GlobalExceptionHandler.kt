package pro.notes.errorhandling

import jakarta.ws.rs.container.ContainerRequestContext
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class GlobalExceptionHandler {
    @ExceptionHandler
    fun handleBusinessExceptions(
        ex: BusinessExceptions,
        requestContext: ContainerRequestContext,
    ): ResponseEntity<ExceptionResponseWrapper> =
        when (ex) {
            is UserNotFound -> createErrorResponse(NOT_FOUND, ex.message)
            is UsernameAlreadyExists -> createErrorResponse(CONFLICT, ex.message)
        }

    private fun createErrorResponse(
        responseCode: HttpStatus,
        errorMessage: String,
    ): ResponseEntity<ExceptionResponseWrapper> = ResponseEntity.status(responseCode).body(ExceptionResponseWrapper(errorMessage))

    data class ExceptionResponseWrapper(
        val errorMessage: String,
    )
}
