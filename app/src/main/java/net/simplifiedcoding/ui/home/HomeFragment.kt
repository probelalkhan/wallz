package net.simplifiedcoding.ui.home

import android.os.Bundle
import android.provider.DocumentsContract.Document.COLUMN_SIZE
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import net.simplifiedcoding.R
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.databinding.FragmentHomeBinding
import net.simplifiedcoding.ui.commons.handleFailure
import net.simplifiedcoding.ui.commons.launchAndRepeatWithViewLifecycle
import net.simplifiedcoding.ui.commons.set
import net.simplifiedcoding.ui.commons.setVisible
import net.simplifiedcoding.utils.GRID_COLUMN_SIZE

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter = HomeRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.recyclerView.set(adapter, GridLayoutManager(requireContext(), GRID_COLUMN_SIZE))

        adapter.itemClickListener = { itemView, item, position ->
            PhotoViewerDialogFragment(item).show(childFragmentManager, "")
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.result.collect { resource ->
                when (resource) {
                    Resource.Loading -> binding.progressBar.setVisible(true)
                    is Resource.Failure -> {
                        binding.progressBar.setVisible(false)
                        handleFailure(resource)
                    }
                    is Resource.Success -> {
                        binding.progressBar.setVisible(false)
                        adapter.items = resource.value
                    }
                }
            }
        }
    }

}