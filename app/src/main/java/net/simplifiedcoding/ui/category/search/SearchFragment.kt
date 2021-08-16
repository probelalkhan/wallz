package net.simplifiedcoding.ui.category.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentSearchBinding
import net.simplifiedcoding.ui.commons.launchAndRepeatWithViewLifecycle
import net.simplifiedcoding.ui.commons.setPaging
import net.simplifiedcoding.ui.home.PhotoViewerDialogFragment
import net.simplifiedcoding.utils.GRID_COLUMN_SIZE
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel by viewModels<SearchViewModel>()

    @Inject
    lateinit var adapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        binding.recyclerView.setPaging(
            adapter,
            GridLayoutManager(requireContext(), GRID_COLUMN_SIZE)
        )

        adapter.itemClickListener = { _, item, position ->
            PhotoViewerDialogFragment(item).show(childFragmentManager, "")
        }

        val query = arguments?.getString(KEY_SEARCH_QUERY) ?: return

        binding.textViewSearchQuery.text = query

        launchAndRepeatWithViewLifecycle {
            viewModel.getSearchResults(query).collect {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        const val KEY_SEARCH_QUERY = "key_search_query"
    }
}