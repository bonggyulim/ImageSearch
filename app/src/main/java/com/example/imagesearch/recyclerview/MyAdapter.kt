package com.example.imagesearch.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.utils.DateFormat
import com.example.imagesearch.databinding.RecyclerviewBinding
import com.example.imagesearch.presentation.MainActivity
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList

class MyAdapter : RecyclerView.Adapter<MyAdapter.Holder>() {
    var items = mutableListOf<Documents>()

    interface ItemClick {
        fun onClick(holder: Holder, position: Int)
    }
    var itemClick: ItemClick? = null

    interface Invisible {
        fun invisible(holder: Holder, position: Int)
    }
    var invisible: Invisible? = null

    class Holder(private val binding: RecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val site = binding.siteName
        val date = binding.date
        val heart = binding.heart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val getItem = items[position]
        val url = getItem.image_url
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.image)

        holder.apply {
            date.text = DateFormat.dateToStirng(getItem.datetime)
            site.text = getItem.display_sitename

            // 예전에 누른 하트 찾기
            if (selectedImageList.contains(items[position])){
                holder.heart.visibility = View.VISIBLE
            } else {
                holder.heart.visibility = View.INVISIBLE
            }

            // 아이템 클릭시 하트 생성 및 보관함 추가
            itemView.setOnClickListener {
                if (selectedImageList.contains(items[position])) {
                    selectedImageList.removeAt(selectedImageList.indexOf(items[position]))
                    heart.visibility = View.INVISIBLE

                } else {
                    itemClick?.onClick(holder, position)
                    heart.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
