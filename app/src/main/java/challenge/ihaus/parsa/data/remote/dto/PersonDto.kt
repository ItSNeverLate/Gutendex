package challenge.ihaus.parsa.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("birth_year")
    val birthYear: Int?,
    @SerializedName("death_year")
    val deathYear: Int?,
    val name: String
)