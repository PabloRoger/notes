package pro.notes.errorhandling

import pro.notes.errorhandling.ErrorMessages.USERNAME_ALREADY_EXISTS
import pro.notes.errorhandling.ErrorMessages.USER_NOT_FOUND
import java.util.UUID

sealed class BusinessExceptions(override val message: String) : RuntimeException(message)

class UserNotFound(userId: UUID) : BusinessExceptions(USER_NOT_FOUND.format(userId))

class UsernameAlreadyExists(username: String) : BusinessExceptions(USERNAME_ALREADY_EXISTS.format(username))
