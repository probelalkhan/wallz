package net.simplifiedcoding.ui.category.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import net.simplifiedcoding.data.repository.WallzRepository
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.utils.DEFAULT_PHOTO_COUNT
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WallzRepository
) : ViewModel() {

    fun getSearchResults(query: String): Flow<PagingData<PhotoResponseItem>> {
        return Pager(
            PagingConfig(pageSize = DEFAULT_PHOTO_COUNT)
        ) {
            SearchDataSource(repository, query)
        }.flow.cachedIn(viewModelScope)
    }
}