/*
package pro.notes.model.utils.types

import com.fasterxml.jackson.annotation.JsonValue
import jakarta.persistence.Embeddable
import jakarta.persistence.MappedSuperclass

val BASIC_TEXT_FIELD_REGEX = Regex("^[\\sa-zA-ZäÄöÖüÜß]{1,50}$")

@MappedSuperclass
@Embeddable
abstract class BasicTextField(
    @get:JsonValue
    val value: String
) {
    protected fun verifyField(identifierName: String, identifierRegex: Regex) {
        if (value.isBlank()) {
            // THROW EXCEPTION
            println("NO PUEDE ESTAR VACIO")
        }

        if (!value.matches(identifierRegex)) {
            //EXCEPTION
            println("ESCRIBELO BIEN")
        }
    }

    override fun toString() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BasicTextField

        return value == other.value
    }

    override fun hashCode() = value.hashCode()
}
*/
