package com.example.imagesearch.mapper

import com.example.imagesearch.database.EntityData
import com.example.imagesearch.recyclerview.Documents

fun mapper(data: Documents): EntityData {
    return EntityData(
        data.image_url,
        data.display_sitename,
        data.datetime
    )
}