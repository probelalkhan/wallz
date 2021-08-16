package net.simplifiedcoding.ui.offline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import net.simplifiedcoding.data.db.OfflinePhoto
import net.simplifiedcoding.databinding.ItemPhotoBinding
import net.simplifiedcoding.ui.commons.BaseRecyclerViewAdapter
import javax.inject.Inject

class OfflineAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<OfflinePhoto, ItemPhotoBinding>() {

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
        holder.binding.imageView.setImageURI(Uri.parse(items[position].uri))
    }

}

