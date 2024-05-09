package com.example.imagesearch.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearch.utils.DateFormat
import com.example.imagesearch.utils.ImageLoader
import com.example.imagesearch.databinding.RectclerviewBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.Holder>() {
    var items = mutableListOf<Documents>()

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    class Holder(private val binding: RectclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val site = binding.siteName
        val date = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RectclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val getItem = items[position]
        val url = getItem.image_url
        ImageLoader.load(url.toString(), holder.image)

        holder.date.text = DateFormat.dateToStirng(getItem.datetime)
        holder.site.text = getItem.display_sitename

        holder.itemView.setOnClickListener{
            itemClick?.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
