package net.simplifiedcoding.ui.home

import android.os.Bundle
import android.view.View
import net.simplifiedcoding.R
import net.simplifiedcoding.data.responses.RandomPhotosResponseItem
import net.simplifiedcoding.databinding.DialogFragmentPhotoViewerBinding
import net.simplifiedcoding.service.PhotoDownloadService
import net.simplifiedcoding.ui.commons.FullWidthDialogFragment

class PhotoViewerDialogFragment(private val item: RandomPhotosResponseItem) :
    FullWidthDialogFragment(R.layout.dialog_fragment_photo_viewer) {

    private lateinit var binding: DialogFragmentPhotoViewerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogFragmentPhotoViewerBinding.bind(view)
        binding.imageView.setImageURI(item.urls.regular)

        binding.imageViewShare.setOnClickListener { }
        binding.imageViewDownload.setOnClickListener {
            PhotoDownloadService.downloadPhoto(requireContext(), item.id, item.urls.regular)
        }
        binding.imageViewClose.setOnClickListener { dismiss() }
    }

}