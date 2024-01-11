package pro.notes.model.user

import java.util.UUID

data class UserResponseDTO(
    val userId: UUID?,
    val username: String,
)
