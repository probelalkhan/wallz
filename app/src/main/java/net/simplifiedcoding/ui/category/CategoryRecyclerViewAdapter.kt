package net.simplifiedcoding.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import net.simplifiedcoding.data.responses.RandomPhotosResponseItem
import net.simplifiedcoding.databinding.ItemPhotoBinding
import net.simplifiedcoding.ui.commons.BaseRecyclerViewAdapter

class HomeRecyclerViewAdapter :
    BaseRecyclerViewAdapter<RandomPhotosResponseItem, ItemPhotoBinding>() {

    override fun getItemLayout(parent: ViewGroup) = ItemPhotoBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemPhotoBinding>,
        position: Int
    ) {
        holder.binding.imageView.setImageURI(items[position].urls.regular)
        holder.binding.root.setOnClickListener { itemClickListener?.invoke(it, items[position], position)}
    }
}