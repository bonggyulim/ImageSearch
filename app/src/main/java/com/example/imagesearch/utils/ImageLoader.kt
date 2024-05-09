package com.example.imagesearch.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.URL
import java.util.concurrent.Executors

object ImageLoader {
    fun load(url : String, view : ImageView){

        val executors = Executors.newSingleThreadExecutor()
        var image : Bitmap? = null

        executors.execute {
            try {
                image = BitmapFactory.decodeStream(URL(url).openStream())
                view.setImageBitmap(image)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}