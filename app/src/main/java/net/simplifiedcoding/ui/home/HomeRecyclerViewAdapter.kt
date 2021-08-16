package net.simplifiedcoding.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.databinding.ItemPhotoBinding
import net.simplifiedcoding.ui.commons.BaseRecyclerViewAdapter
import javax.inject.Inject

class HomeRecyclerViewAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<PhotoResponseItem, ItemPhotoBinding>() {

    override fun getItemLayout(parent: ViewGroup) = ItemPhotoBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemPhotoBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.binding.imageView.setImageURI(items[position].urls.regular)
    }
}