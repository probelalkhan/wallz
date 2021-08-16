package net.simplifiedcoding.ui.offline

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.simplifiedcoding.data.repository.WallzRepository
import javax.inject.Inject

@HiltViewModel
class OfflineViewModel @Inject constructor(
    private val repo: WallzRepository
) : ViewModel() {
    val offlinePhotos = repo.getOfflinePhotos()
}