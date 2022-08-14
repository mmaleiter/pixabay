package com.pixabay.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.pixabay.MainViewModel
import com.pixabay.R
import com.pixabay.databinding.FragmentDetailScreenBinding
import com.pixabay.ui.base.viewDataBinding

class DetailFragment : Fragment(R.layout.fragment_detail_screen) {

    private val activityViewModel: MainViewModel by activityViewModels()

    private val binding by viewDataBinding {
        FragmentDetailScreenBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            detailItem = activityViewModel.detailItem
            lifecycleOwner = viewLifecycleOwner
        }
    }
}