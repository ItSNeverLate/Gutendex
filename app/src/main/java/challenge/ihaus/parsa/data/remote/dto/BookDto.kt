package challenge.ihaus.parsa.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val authors: List<PersonDto>,
    val translators: List<PersonDto>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    @SerializedName("media_type")
    val mediaType: String,
    val formats: Map<String, String>,
    @SerializedName("media_type")
    val downloadCount: Int,
)