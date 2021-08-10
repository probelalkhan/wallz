package net.simplifiedcoding.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.WallzRepository
import net.simplifiedcoding.data.responses.RandomPhotosResponseItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: WallzRepository
) : ViewModel() {

    val result: StateFlow<Resource<List<RandomPhotosResponseItem>>> = flow {
        emit(repo.getRandomPhotos())
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = Resource.Loading
    )

}