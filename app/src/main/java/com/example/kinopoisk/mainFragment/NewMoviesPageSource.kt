package com.example.kinopoisk.mainFragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.kinopoisk.mainFragment.api.ApiServiceMainFragment
import com.example.kinopoisk.mainFragment.models.Movies

class NewMoviesPageSource(
    private val service: ApiServiceMainFragment,
) : PagingSource<Int, Movies>() {
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        try {
            val page: Int = params.key ?: 1
            val pageSize: Int = params.loadSize.coerceAtMost(20)
            val response = service.getNewMovies(page, pageSize)

            if (response.isSuccessful) {
                val movies = checkNotNull(response.body()).docs
                val nextKey = if (movies.size < pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                return LoadResult.Page(movies, prevKey, nextKey)
            } else {
                return LoadResult.Error(HttpException(response.code()))
            }
        } catch (e: retrofit2.HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}