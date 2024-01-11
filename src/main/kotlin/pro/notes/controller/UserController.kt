package pro.notes.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pro.notes.model.user.UserEntity
import pro.notes.model.user.UserRequestDTO
import pro.notes.model.user.UserResponseDTO
import pro.notes.services.UserService
import java.util.UUID

@RestController
@RequestMapping("/user-api")
class UserController(
    val userService: UserService,
) {
    @GetMapping("/users")
    @ResponseStatus(OK)
    fun getAllUsers(): List<UserResponseDTO> {
        val users =
            userService
                .getAllUser()
                .map {
                    UserResponseDTO(it.userId, it.username)
                }

        userService.sendToRabbit("$users")
        return users
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(OK)
    fun getUserById(
        @PathVariable userId: UUID,
    ): UserEntity {
        val user = userService.getUserById(userId)

        userService.sendToRabbit("$user")
        return user
    }

    @PostMapping("/user")
    @ResponseStatus(CREATED)
    fun createUser(
        @RequestBody userEntity: UserRequestDTO,
    ): UserResponseDTO {
        val newUser =
            userService
                .createUser(userEntity)

        userService.sendToRabbit("$newUser")
        return newUser
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(NO_CONTENT)
    fun deleteUser(
        @PathVariable userId: UUID,
    ) {
        userService.deleteUser(userId)
    }
}
