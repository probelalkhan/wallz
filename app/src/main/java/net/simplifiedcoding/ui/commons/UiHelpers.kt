package net.simplifiedcoding.ui.commons

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource


fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


fun RecyclerView.set(
    adapter: BaseRecyclerViewAdapter<*, *>,
    layoutManager: RecyclerView.LayoutManager
) {
    this.setHasFixedSize(true)
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun Fragment.handleFailure(failure: Resource.Failure, retry: () -> Unit = {}) {
    CommonErrorFragment(retry).show(childFragmentManager, "")
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