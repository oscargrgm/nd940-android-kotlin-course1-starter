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
import com.udacity.shoestore.ui.MainActivityViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var viewModel: MainActivityViewModel

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

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeDetailCancelBt.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment()
            )
        }

        binding.shoeDetailAcceptBt.setOnClickListener {
            viewModel.isValidShoe(
                binding.shoeDetailNameEt.text.toString(),
                binding.shoeDetailCompanyEt.text.toString(),
                binding.shoeDetailSizeEt.text.toString(),
                binding.shoeDetailDescriptionEt.text.toString()
            )

            findNavController().navigate(
                ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToShoeListingFragment()
            )
        }

        return binding.root
    }
}