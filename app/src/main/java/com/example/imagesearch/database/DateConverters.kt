package com.example.imagesearch.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

@ProvidedTypeConverter
class DateConverters(private val gson: Gson) {
    @TypeConverter
    fun dateToString(date: Date?): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(string: String?): Date? {
        return gson.fromJson(string, Date::class.java)
    }
}