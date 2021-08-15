package net.simplifiedcoding.ui.category.search

import androidx.paging.*
import net.simplifiedcoding.data.repository.WallzRepository
import net.simplifiedcoding.data.responses.PhotoResponseItem

class SearchMediator(
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
      
    }

}