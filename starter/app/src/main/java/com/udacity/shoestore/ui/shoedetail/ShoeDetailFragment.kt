package com.udacity.shoestore.ui.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.ui.MainActivity

class ShoeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        val viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeDetailCancelBt.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment()
            )
        }

        viewModel.isShoeAdded.observe(viewLifecycleOwner) { isValidShoe ->
            if (isValidShoe) {
                // Add shoe into the MainActivity shoe list.
                (activity as MainActivity).viewModel.shoeList.value?.add(viewModel.shoe)
                findNavController().navigate(
                    ShoeDetailFragmentDirections
                        .actionShoeDetailFragmentToShoeListingFragment()
                )
            }
        }

        setHasOptionsMenu(false)
        return binding.root
    }
}