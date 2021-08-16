package net.simplifiedcoding.ui.category

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentCategoryBinding
import net.simplifiedcoding.ui.category.search.SearchFragment
import net.simplifiedcoding.ui.commons.set
import net.simplifiedcoding.ui.commons.snackbar
import net.simplifiedcoding.utils.getLocalCategories
import javax.inject.Inject


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding

    @Inject
    lateinit var adapter: CategoryRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)
        binding.recyclerViewCategories.set(adapter, LinearLayoutManager(requireContext()))
        adapter.items = getLocalCategories()

        adapter.itemClickListener = { itemView, item, position ->
            searchPhotos(item.name)
        }

        binding.editTextSearch.setOnEditorActionListener { textView, action, event ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.editTextSearch.text.toString().trim()
                if (query.isEmpty()) {
                    snackbar("Write what you want to search...")
                }else {
                    searchPhotos(query)
                }
            }
            true
        }
    }

    private fun searchPhotos(query: String) {
        findNavController().navigate(
            R.id.searchFragment,
            bundleOf(SearchFragment.KEY_SEARCH_QUERY to query)
        )
    }
}