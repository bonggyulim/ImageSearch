package com.example.imagesearch.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.databinding.RecyclerviewBinding
import com.example.imagesearch.utils.DateFormat

class SelectedAdapter(): RecyclerView.Adapter<SelectedAdapter.SelectedHolder>() {
    var items : MutableList<Documents> = mutableListOf()

    interface ItemClick {
        fun onClick(position: Int)
    }
    var itemClick: ItemClick? = null

    class SelectedHolder(private val binding: RecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val date = binding.date
        val site = binding.siteName
        val heart = binding.heart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedHolder {
        val binding = RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SelectedHolder, position: Int) {
        holder.itemView.setOnClickListener {
            items.removeAt(position)
            notifyItemRemoved(position)
            itemClick?.onClick(position)
            holder.heart.visibility = View.INVISIBLE
        }

        val getItem = items.get(position)
        val url = getItem.image_url
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.image)

        holder.date.text = DateFormat.dateToStirng(getItem.datetime)
        holder.site.text = getItem.display_sitename
    }


}
