package com.udacity.shoestore.ui.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        val viewModel = (activity as MainActivity).viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeDetailCancelBt.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment()
            )
        }

        binding.shoeDetailAcceptBt.setOnClickListener {
            viewModel.isValidShoe(
                name = binding.shoeDetailNameEt.text.toString(),
                size = binding.shoeDetailSizeEt.text.toString(),
                company = binding.shoeDetailCompanyEt.text.toString(),
                description = binding.shoeDetailDescriptionEt.text.toString()
            )

            findNavController().navigate(
                ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToShoeListingFragment()
            )
        }

        setHasOptionsMenu(false)
        return binding.root
    }
}