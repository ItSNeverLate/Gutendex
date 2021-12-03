package challenge.ihaus.parsa.data.remote.dto

data class BookDto(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val authors: List<PersonDto>,
    val translators: List<PersonDto>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    val media_type: String,
    val formats: Map<String, String>,
    val download_count: Int,
)