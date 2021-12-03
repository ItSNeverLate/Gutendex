package challenge.ihaus.parsa.data.local.entity

import androidx.room.Entity

@Entity(tableName = "books")
data class BookEntity(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val authors: List<PersonEntity>,
    val translators: List<PersonEntity>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    val mediaType: String,
    val otherInformation: Map<String,String>,
    val downloadCount: Int,
    val imageUrl: String
)