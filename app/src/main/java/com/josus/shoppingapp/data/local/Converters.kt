package com.josus.shoppingapp.data.local


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import com.josus.shoppingapp.data.util.JsonParser
import com.josus.shoppingapp.data.model.Rating

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromRatingsJson(json:String):List<Rating>{
        return jsonParser.fromJson<ArrayList<Rating>>(
            json,
            object : TypeToken<ArrayList<Rating>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toRatingsJson(ratings:List<Rating>):String{
        return jsonParser.toJson(
            ratings,
            object : TypeToken<ArrayList<Rating>>() {}.type
        ) ?: "[]"
    }
}