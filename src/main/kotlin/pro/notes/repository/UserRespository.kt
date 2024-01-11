package pro.notes.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pro.notes.model.user.UserEntity
import java.util.UUID

@Repository
interface UserRespository : JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    fun findByUsername(
        @Param("username") username: String,
    ): UserEntity?

    @Query("SELECT u FROM UserEntity u WHERE u.userId = :userId")
    fun findByUUID(
        @Param("userId") userId: UUID,
    ): UserEntity?
}
