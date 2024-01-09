package pro.notes.model.notes

import java.time.LocalDate
import java.util.UUID

data class NotesDTO(
    val noteId: UUID,
    val userId: UUID,
    val title: String,
    val content: String,
    val creationDate: LocalDate
)
