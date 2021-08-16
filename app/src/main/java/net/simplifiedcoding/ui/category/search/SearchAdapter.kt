package net.simplifiedcoding.ui.category.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.databinding.ItemPhotoBinding
import javax.inject.Inject


class SearchAdapter @Inject constructor() :
    PagingDataAdapter<PhotoResponseItem, SearchAdapter.SearchViewHolder>(
        PhotoResponseItemsComparator
    ) {

    var itemClickListener: ((itemView: View, item: PhotoResponseItem, position: Int) -> Unit)? =
        null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        return SearchViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.imageView.setImageURI(getItem(position)?.urls?.regular)
        holder.binding.root.setOnClickListener { view ->
            getItem(position)?.let { item -> itemClickListener?.invoke(view, item, position) }
        }
    }

    inner class SearchViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    object PhotoResponseItemsComparator : DiffUtil.ItemCallback<PhotoResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PhotoResponseItem,
            newItem: PhotoResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoResponseItem,
            newItem: PhotoResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}