package com.pixabay.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pixabay.MainViewModel
import com.pixabay.R
import com.pixabay.databinding.FragmentHomeBinding
import com.pixabay.ui.base.viewDataBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.net.UnknownHostException

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val activityViewModel: MainViewModel by activityViewModels()

    private val binding by viewDataBinding {
        FragmentHomeBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = activityViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.adapter = ImageListAdapter(activityViewModel)

        activityViewModel.showDetailScreen.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
            }
        }
        activityViewModel.showError.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { throwable ->
                when(throwable){
                    is HttpException ->
                        Snackbar.make(binding.root,
                            "Your api key is invalid or not provided.", Snackbar.LENGTH_LONG).show()
                    is UnknownHostException ->
                        Snackbar.make(binding.root,
                            "There is no internet connection.", Snackbar.LENGTH_LONG).show()
                    else -> Snackbar.make(binding.root,
                        "Unknown error. Showing last search result.", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}