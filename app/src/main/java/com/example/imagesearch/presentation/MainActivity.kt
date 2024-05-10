package com.example.imagesearch.presentation

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imagesearch.MyItem
import com.example.imagesearch.R
import com.example.imagesearch.SearchImage
import com.example.imagesearch.databinding.ActivityMainBinding
import com.example.imagesearch.recyclerview.Documents
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), SearchImage.FragmentTextDataListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object SelectedImageList {
        var selectedImageList = mutableListOf<Documents>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViewPager()
    }

    private fun initViewPager() {
        var viewPagerAdatper = ViewPagerAdapter(this)
        val searchImage = SearchImage.newInstance(loadData())

        viewPagerAdatper.addFragment(searchImage)
        viewPagerAdatper.addFragment(MyItem())

        binding.viewPager.apply {
            adapter = viewPagerAdatper
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "이미지 검색"
                1 -> tab.text = "내 보관함"
            }
        }.attach()
    }

    override fun onDataReceived(data: String) {
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("data", data)
        edit.apply()
    }

    private fun loadData(): String {
        val pref = getSharedPreferences("pref", 0)
        val loadData = pref.getString("data", "").toString()
        return loadData
    }

    private fun saveList() {
        val jsonValue = Gson().toJson(selectedImageList)
        val pref = getSharedPreferences("pref2", 0)
        pref.edit().apply{
            putString("json", jsonValue)
            apply()
        }
    }

    private fun loadList() {
        val pref = getSharedPreferences("pref2", 0)
        val loadList = pref.getString("json", "")
        val type = object : TypeToken<MutableList<Documents>>() {}.type
        selectedImageList = Gson().fromJson(loadList, type)

    }

}