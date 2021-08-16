package net.simplifiedcoding.ui.offline

import android.os.Bundle
import android.view.View
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.DialogFragmentPhotoViewerBinding
import net.simplifiedcoding.ui.commons.FullWidthDialogFragment
import net.simplifiedcoding.ui.commons.setVisible

class OfflinePhotoViewerDialogFragment(private val uri: String) :
  FullWidthDialogFragment(R.layout.dialog_fragment_photo_viewer) {

  private lateinit var binding: DialogFragmentPhotoViewerBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = DialogFragmentPhotoViewerBinding.bind(view)
    binding.imageView.setImageURI(uri)
    binding.imageViewClose.setOnClickListener { dismiss() }
    binding.imageViewDownload.setVisible(false)
  }
}