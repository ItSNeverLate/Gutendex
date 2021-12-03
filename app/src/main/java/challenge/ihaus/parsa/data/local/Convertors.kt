package challenge.ihaus.parsa.data.local

import androidx.room.TypeConverter
import challenge.ihaus.parsa.data.local.entity.PersonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Convertors {

    private val gson = Gson()

    // Save and Retrieve Map<String,String>
    @TypeConverter
    fun jsonToMap(json: String): Map<String, String> {
        val listType = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun mapToJson(map: Map<String, String>): String {
        return gson.toJson(map)
    }

    // Save and Retrieve List<PersonEntity>
    @TypeConverter
    fun jsonToPersonEntityList(json: String): List<PersonEntity> {
        val listType = object : TypeToken<List<PersonEntity>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun personEntityListToJson(list: List<PersonEntity>): String {
        return gson.toJson(list)
    }

    // Save and Retrieve List<String>
    @TypeConverter
    fun jsonToStringList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun stringListToJson(list: List<String>): String {
        return gson.toJson(list)
    }
}