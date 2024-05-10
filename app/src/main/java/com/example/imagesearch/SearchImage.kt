package com.example.imagesearch

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.databinding.FragmentSearchImageBinding
import com.example.imagesearch.presentation.MainActivity
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList
import com.example.imagesearch.presentation.SearchViewModel
import com.example.imagesearch.presentation.SearchViewModelFactory
import com.example.imagesearch.recyclerview.Documents
import com.example.imagesearch.recyclerview.MyAdapter
import com.example.imagesearch.recyclerview.SelectedAdapter
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"

class SearchImage : Fragment() {
    private lateinit var binding: FragmentSearchImageBinding
    private var param1: String? = null
    private var listener: FragmentTextDataListener? = null
    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }
    private val adapter = MyAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentTextDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchImageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (param1 != null) {
            binding.searchEt.setText(param1)
        }

        searchViewModel.getDataList.observe(viewLifecycleOwner) { list ->
            adapter.items = list as MutableList<Documents>
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = GridLayoutManager(context as Activity, 2)

            adapter.itemClick = object : MyAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    selectedImageList.add(list[position])
                    Log.d("adapterItem", "${list[position]}")
                }
            }
        }
        btnClick()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SearchImage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }


    private fun communicateNetWork(query: String) {
        searchViewModel.getDataList(query)
    }

    fun btnClick() {
        binding.searchBtn.setOnClickListener {
            if (binding.searchEt.text.isEmpty()) {
                Toast.makeText(requireContext(), "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                fun hideKeyboard(activity: Activity){
                    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
                }
                hideKeyboard(context as Activity)

                val text = binding.searchEt.text.toString()
                communicateNetWork(text)

                // 마지막 검색기록 저장
                listener?.onDataReceived(text)
            }
        }
    }


    interface FragmentTextDataListener {
        fun onDataReceived(data : String)
    }

}