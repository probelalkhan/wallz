package net.simplifiedcoding.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import net.simplifiedcoding.data.Category
import net.simplifiedcoding.databinding.ItemCategoryBinding
import net.simplifiedcoding.ui.commons.BaseRecyclerViewAdapter
import net.simplifiedcoding.utils.getBitmapFromAsset
import javax.inject.Inject

class CategoryRecyclerViewAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<Category, ItemCategoryBinding>() {

    override fun getItemLayout(parent: ViewGroup) = ItemCategoryBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemCategoryBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        val bitmap = holder.binding.root.context.getBitmapFromAsset(items[position].assetName)
        holder.binding.imageView.setImageBitmap(bitmap)
        holder.binding.textViewCategoryName.text = items[position].name
    }
}