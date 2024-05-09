package com.example.imagesearch.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
import java.util.Date

@Entity(tableName = "data_table")
data class EntityData(
    @PrimaryKey
    val image_url: URL,
    val display_sitename: String?,
    val datetime: Date?
)
