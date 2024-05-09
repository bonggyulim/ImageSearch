package com.example.imagesearch.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearch.databinding.RectclerviewBinding
import com.example.imagesearch.presentation.MainActivity
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList
import com.example.imagesearch.utils.DateFormat
import com.example.imagesearch.utils.ImageLoader

class SelectedAdapter(): RecyclerView.Adapter<SelectedAdapter.SelectedHolder>() {
    var items : MutableList<Documents> = mutableListOf()

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    class SelectedHolder(private val binding: RectclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val site = binding.siteName
        val date = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedHolder {
        val binding = RectclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        selectedImageList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, itemCount - position)
    }

    override fun onBindViewHolder(holder: SelectedHolder, position: Int) {
        holder.itemView.setOnClickListener {
            selectedImageList.removeAt(position)
            notifyDataSetChanged()
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount - position)
        }

        val getItem = items.get(position)
        val url = getItem.image_url

        ImageLoader.load(url.toString(), holder.image)
        holder.date.text = DateFormat.dateToStirng(getItem.datetime)
        holder.site.text = getItem.display_sitename


    }
}
