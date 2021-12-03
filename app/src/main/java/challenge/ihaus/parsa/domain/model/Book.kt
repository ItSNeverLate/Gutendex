package challenge.ihaus.parsa.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val authors: List<Person>,
    val translators: List<Person>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    val mediaType: String,
    val otherInformation: Map<String,String>,
    val downloadCount: Int,
    val imageUrl: String
):Parcelable