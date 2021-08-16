package net.simplifiedcoding.ui.category.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.WallzRepository
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.utils.DEFAULT_PHOTO_COUNT

class SearchDataSource(
    private val repository: WallzRepository,
    private val query: String
) : PagingSource<Int, PhotoResponseItem>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponseItem> {
        val next = params.key ?: 1
        return when (val response = repository.getSearchResult(query, next, DEFAULT_PHOTO_COUNT)) {
            is Resource.Failure -> LoadResult.Error(response.throwable)
            is Resource.Success -> LoadResult.Page(
                data = response.value.results,
                prevKey = null,
                nextKey = if (next < response.value.total_pages) next.plus(1) else null
            )
            else -> throw IllegalArgumentException("Invalid State")
        }
    }

}