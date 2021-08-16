package net.simplifiedcoding.ui.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.FragmentCommonErrorBinding

class CommonErrorFragment :
    Fragment(R.layout.fragment_common_error) {

    private lateinit var binding: FragmentCommonErrorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommonErrorBinding.bind(view)
        binding.buttonTryAgain.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}