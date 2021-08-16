package net.simplifiedcoding.ui.home

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import net.simplifiedcoding.R
import net.simplifiedcoding.data.responses.PhotoResponseItem
import net.simplifiedcoding.databinding.DialogFragmentPhotoViewerBinding
import net.simplifiedcoding.ui.commons.FullWidthDialogFragment
import net.simplifiedcoding.utils.DEFAULT_PHOTO_EXTENSION

class PhotoViewerDialogFragment(private val item: PhotoResponseItem) :
    FullWidthDialogFragment(R.layout.dialog_fragment_photo_viewer) {

    private lateinit var binding: DialogFragmentPhotoViewerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogFragmentPhotoViewerBinding.bind(view)
        binding.imageView.setImageURI(item.urls.regular)

        binding.imageViewShare.setOnClickListener { }
        binding.imageViewDownload.setOnClickListener {
            downloadPhoto(
                item.id + DEFAULT_PHOTO_EXTENSION,
                item.description,
                item.urls.raw
            )
        }
        binding.imageViewClose.setOnClickListener { dismiss() }
    }

    private fun downloadPhoto(fileName: String, desc: String, url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(fileName)
            .setDescription(desc)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(false)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadID = downloadManager.enqueue(request)
    }
}