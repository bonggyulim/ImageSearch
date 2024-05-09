package com.example.imagesearch

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.databinding.FragmentMyItemBinding
import com.example.imagesearch.presentation.MainActivity
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList
import com.example.imagesearch.recyclerview.SelectedAdapter

class MyItem : Fragment() {
    private lateinit var binding: FragmentMyItemBinding
    private val adapter = SelectedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItem()
    }

    private fun setItem() {
        adapter.items = selectedImageList
        binding.myitemRv.adapter = adapter
        binding.myitemRv.layoutManager = GridLayoutManager(context as Activity, 2)

    }

    override fun onPause() {
        super.onPause()

        setItem()

    }

}