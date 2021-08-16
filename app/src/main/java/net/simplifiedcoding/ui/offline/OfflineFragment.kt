package net.simplifiedcoding.ui.offline

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentOfflineBinding
import net.simplifiedcoding.ui.commons.launchAndRepeatWithViewLifecycle
import net.simplifiedcoding.ui.commons.set
import net.simplifiedcoding.utils.GRID_COLUMN_SIZE
import javax.inject.Inject


@AndroidEntryPoint
class OfflineFragment : Fragment(R.layout.fragment_offline) {

    private lateinit var binding: FragmentOfflineBinding

    @Inject
    lateinit var adapter: OfflineAdapter

    private val viewModel by viewModels<OfflineViewModel>()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOfflineBinding.bind(view)
        binding.recyclerView.set(adapter, GridLayoutManager(requireContext(), GRID_COLUMN_SIZE))
        adapter.itemClickListener = { itemView, item, position ->
            OfflinePhotoViewerDialogFragment(item.uri).show(childFragmentManager, "")
        }
        launchAndRepeatWithViewLifecycle {
            viewModel.offlinePhotos.collect {
                adapter.items = it
            }
        }
    }
}