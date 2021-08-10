package net.simplifiedcoding.ui.commons

import android.os.Bundle
import android.view.View
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentCommonErrorBinding

class CommonErrorFragment(private val retry: () -> Unit) :
    FullWidthDialogFragment(R.layout.fragment_common_error) {

    private lateinit var binding: FragmentCommonErrorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommonErrorBinding.bind(view)
        binding.buttonTryAgain.setOnClickListener {
            dismiss()
            retry()
        }
    }
}