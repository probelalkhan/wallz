package net.simplifiedcoding.ui.commons

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.R
import net.simplifiedcoding.data.network.Resource


fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Fragment.snackbar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}


fun RecyclerView.set(
    adapter: BaseRecyclerViewAdapter<*, *>,
    layoutManager: RecyclerView.LayoutManager
) {
    this.setHasFixedSize(true)
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun RecyclerView.setPaging(
    adapter: PagingDataAdapter<*, *>,
    layoutManager: RecyclerView.LayoutManager
) {
    this.setHasFixedSize(true)
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun Fragment.handleFailure(failure: Resource.Failure) {
    findNavController().also {
        it.popBackStack()
    }.navigate(R.id.commonErrorFragment)
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}