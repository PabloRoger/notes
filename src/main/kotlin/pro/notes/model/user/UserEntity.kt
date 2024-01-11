package pro.notes.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @Column(name = "user_id")
    val userId: UUID = UUID.randomUUID(),
    @Column(name = "username")
    var username: String,
    @Column(name = "password_hash")
    var password: String,
)

fun UserEntity.toDTO() =
    UserDTO(
        username = this.username,
        password = this.password,
    )
