package challenge.ihaus.parsa.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val authors: List<PersonEntity>,
    val authorBirthYear: Int?,
    val authorDeathYear: Int?,
    val translators: List<PersonEntity>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    val mediaType: String,
    val otherInformation: Map<String, String>,
    val downloadCount: Int,
) {
    var isFavorite: Boolean = false
}