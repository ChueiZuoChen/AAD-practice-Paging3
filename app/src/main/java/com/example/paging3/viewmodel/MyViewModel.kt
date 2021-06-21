package com.example.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.data.Result
import com.example.paging3.data.RickAndMortyList
import com.example.paging3.network.RetroInstance
import com.example.paging3.network.RetroService
import com.example.paging3.paging.ResutltPagingResource
import kotlinx.coroutines.flow.Flow


class MyViewModel: ViewModel() {
    lateinit var retroService: RetroService
    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 34,maxSize = 200),
        pagingSourceFactory = {ResutltPagingResource(retroService)}).flow.cachedIn(viewModelScope)

    }
}