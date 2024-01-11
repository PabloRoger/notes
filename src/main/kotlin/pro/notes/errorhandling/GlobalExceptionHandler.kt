package pro.notes.errorhandling

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandle : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BusinessExceptions::class)
    fun handleBusinessExceptions(
        ex: BusinessExceptions,
        requestContext: WebRequest,
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
