package net.simplifiedcoding.ui.offline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import net.simplifiedcoding.databinding.ItemPhotoBinding
import net.simplifiedcoding.ui.commons.BaseRecyclerViewAdapter
import java.io.File

class OfflineAdapter :
    BaseRecyclerViewAdapter<String, ItemPhotoBinding>() {

    override fun getItemLayout(parent: ViewGroup) = ItemPhotoBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemPhotoBinding>,
        position: Int
    ) {
        holder.binding.imageView.setImageURI(items[position].getLocalUriForImage())
    }

    private fun String.getLocalUriForImage(): Uri {
        return Uri.fromFile(File(this))
    }

}

