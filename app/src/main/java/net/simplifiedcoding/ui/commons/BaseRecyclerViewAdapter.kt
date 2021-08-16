package net.simplifiedcoding.ui.commons

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T : Any, VB : ViewBinding>
    : RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VB>>() {

    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((itemView: View, item: T, position: Int) -> Unit)? = null

    abstract fun getItemLayout(parent: ViewGroup): VB
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(getItemLayout(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        holder.binding.root.setOnClickListener {
            itemClickListener?.invoke(it, items[position], position)
        }
    }

    companion object {
        class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)
    }
}