package pro.notes.services

import org.springframework.stereotype.Service
import pro.notes.model.user.UserEntity
import pro.notes.model.user.UserRequestDTO
import pro.notes.model.user.UserResponseDTO
import pro.notes.rabbitmq.Data
import pro.notes.rabbitmq.publisher.Publisher
import pro.notes.repository.UserRespository

@Service
class UserService (
    val userRepository: UserRespository,
    val publisher: Publisher
){
    fun getAllUser(): List<UserEntity> = userRepository.findAll()

    fun createUser(userEntity: UserRequestDTO): UserResponseDTO{
        if(userRepository.findByUsername(userEntity.username) != null){
            throw IllegalArgumentException("El nombre de usuario ya está en uso.")
        }

        val newUser = UserEntity(username = userEntity.username, password = userEntity.password)
        userRepository.save(newUser)

        return UserResponseDTO(
            userId = newUser.userId,
            username = newUser.username,
        )
    }

    fun sendToRabbit(message: String){
        println("Message: $message will be send...")
        val data = Data(1, message)
        publisher.send(data)
    }
}