package pro.notes.model.notes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "notes")
class NotesEntity(
    @Id
    @Column(name = "note_id")
    val noteId: UUID,
    @Column(name = "user_id")
    val userId: UUID,
    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String,
    @Column(name = "creation_date")
    val creationDate: LocalDate,
)

fun NotesEntity.toDTO() =
    NotesDTO(
        noteId = this.noteId,
        userId = this.userId,
        title = this.title,
        content = this.content,
        creationDate = this.creationDate,
    )
