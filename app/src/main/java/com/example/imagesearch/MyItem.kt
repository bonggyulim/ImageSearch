package com.example.imagesearch

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.databinding.FragmentMyItemBinding
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList
import com.example.imagesearch.presentation.SearchViewModel
import com.example.imagesearch.presentation.SearchViewModelFactory
import com.example.imagesearch.recyclerview.MyAdapter
import com.example.imagesearch.recyclerview.SelectedAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MyItem : Fragment() {
    private lateinit var binding: FragmentMyItemBinding
    private val adapter = SelectedAdapter()
    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.items = selectedImageList
        binding.myitemRv.adapter = adapter
        binding.myitemRv.layoutManager = GridLayoutManager(context as Activity, 2)

    }

    override fun onPause() {
        super.onPause()
        binding.myitemRv.adapter = adapter
    }

}