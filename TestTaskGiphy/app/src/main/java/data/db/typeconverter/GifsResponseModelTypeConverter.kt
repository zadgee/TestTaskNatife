package data.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.model.GifData
import data.model.GifsResponseModel

class GifsResponseModelTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun gifsToString(gifsResponseModel: List<GifData>): String {
        return gson.toJson(gifsResponseModel)
    }

    @TypeConverter
    fun stringToGifs(string: String): List<GifData> {
        val objectType = object : TypeToken<List<GifData>>() {}.type
        return gson.fromJson(string, objectType)
    }
}