package pro.notes.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pro.notes.model.user.UserRequestDTO
import pro.notes.model.user.UserResponseDTO
import pro.notes.services.UserService

@RestController
@RequestMapping("/user-api")
class UserController(
    val userService: UserService,
) {

    @GetMapping("/users")
    @ResponseStatus(OK)
    fun getAllUsers(): List<UserResponseDTO> {
        val users = userService
            .getAllUser()
            .map {
                UserResponseDTO(it.userId, it.username)
            }

        userService.sendToRabbit("$users")
        return users
    }

    @PostMapping("/user")
    @ResponseStatus(CREATED)
    fun createUser(@RequestBody userEntity: UserRequestDTO): UserResponseDTO {
        val newUser = userService
            .createUser(userEntity)

        userService.sendToRabbit("$newUser")
        return newUser
    }
}