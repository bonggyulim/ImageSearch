package com.example.imagesearch.utils

import java.text.SimpleDateFormat
import java.util.Date


object DateFormat {

    fun dateToStirng(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val _date = format.format(date)
        return _date.toString()
    }
}