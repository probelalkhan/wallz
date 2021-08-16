package net.simplifiedcoding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.db.OfflinePhoto
import net.simplifiedcoding.data.repository.WallzRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repo: WallzRepository
) : ViewModel() {

    fun addOfflinePhoto(photo: OfflinePhoto) =
        viewModelScope.launch { repo.addOfflinePhoto(photo) }
}