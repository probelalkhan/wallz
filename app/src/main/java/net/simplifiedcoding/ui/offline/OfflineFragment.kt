package net.simplifiedcoding.ui.offline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentOfflineBinding
import net.simplifiedcoding.ui.commons.set
import net.simplifiedcoding.utils.GRID_COLUMN_SIZE


@AndroidEntryPoint
class OfflineFragment : Fragment(R.layout.fragment_offline) {

    private lateinit var binding: FragmentOfflineBinding
    private val adapter = OfflineAdapter()
    private val photos = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOfflineBinding.bind(view)
        binding.recyclerView.set(adapter, GridLayoutManager(requireContext(), GRID_COLUMN_SIZE))
        val files = context?.filesDir?.listFiles() ?: arrayOf()
        for (i in files.indices) {
            photos.add(files[i].path)
        }
        adapter.items = photos
    }
}