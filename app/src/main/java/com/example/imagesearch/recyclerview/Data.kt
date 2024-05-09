package com.example.imagesearch.recyclerview

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.Date

data class Data(
    val meta: Meta?,
    val documents: List<Documents>?
)

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)

data class Documents(
    val image_url: URL,
    val display_sitename: String,
    val datetime: Date
)