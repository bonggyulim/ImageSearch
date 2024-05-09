package com.example.imagesearch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.imagesearch.mapper.SearchRepository
import com.example.imagesearch.network.NetworkClient
import com.example.imagesearch.presentation.MainActivity.SelectedImageList.selectedImageList
import com.example.imagesearch.recyclerview.Documents
import com.example.imagesearch.remote.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository): ViewModel() {
    private val _getDataList: MutableLiveData<List<Documents>?> = MutableLiveData()
    val getDataList: MutableLiveData<List<Documents>?> get() = _getDataList
    fun getDataList(query: String) = viewModelScope.launch {
        _getDataList.value = searchRepository.getDataList(query).documents
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(NetworkClient.myApi)
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {

        return SearchViewModel(
            repository
        ) as T
    }
}