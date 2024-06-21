package com.example.ezeats.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ezeats.response.SearchItem

class UserRecipePaging(private val apiService: ApiService): PagingSource<Int, SearchItem>(){
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getSearchRecipeList(page, params.loadSize)

            LoadResult.Page(
                data = responseData.search?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.search.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}