package com.example.imagesearch.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.net.URL

@ProvidedTypeConverter
class URLConverters(private val gson: Gson) {
    @TypeConverter
    fun urlToString(url: URL?): String {
        return url.toString()
    }

    @TypeConverter
    fun stringToUrl(string: String?): URL? {
        return gson.fromJson(string, URL::class.java)
    }
}