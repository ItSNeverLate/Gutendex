package challenge.ihaus.parsa.data.local

import androidx.room.TypeConverter
import challenge.ihaus.parsa.data.local.entity.PersonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Convertors {

    private val gson = Gson()

    @TypeConverter
    fun toAuthorEntity(data: String?): PersonEntity? =
        data?.let {
            val listType = object : TypeToken<PersonEntity>() {}.type
            return gson.fromJson(it, listType)
        }

    @TypeConverter
    fun fromAuthorEntity(author: PersonEntity): String = gson.toJson(author)
}