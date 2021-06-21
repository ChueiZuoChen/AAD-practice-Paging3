package com.example.paging3.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.data.Result
import com.example.paging3.network.RetroService
import java.lang.Exception

class ResutltPagingResource(val apiService: RetroService) : PagingSource<Int, Result>() {
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromAPI(nextPage)
            var nextPageNumber:Int? = null
            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}