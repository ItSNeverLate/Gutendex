package challenge.ihaus.parsa.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birthYear: Int?,
    val deathYear: Int?,
    val name: String
):Parcelable