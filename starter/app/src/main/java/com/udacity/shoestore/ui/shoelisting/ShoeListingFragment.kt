package com.udacity.shoestore.ui.shoelisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.ui.MainActivityViewModel

class ShoeListingFragment : Fragment() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeListingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_listing,
            container,
            false
        )

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.viewModel = mainActivityViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeListingFab.setOnClickListener {
            findNavController().navigate(
                ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
            )
        }

        mainActivityViewModel.shoeList.observe(viewLifecycleOwner) { newList ->
            val shoeLayout = View.inflate(requireContext(), R.layout.layout_shoe, null)
            shoeLayout.run {
                with(newList.last()) {
                    findViewById<TextView>(R.id.shoe_item_name_tv).text = name
                    findViewById<TextView>(R.id.shoe_item_company_tv).text = company
                    findViewById<TextView>(R.id.shoe_item_size_tv).text = size.toString()
                    findViewById<TextView>(R.id.shoe_item_description_tv).text = description
                }
            }

            binding.shoeListingLayout.addView(shoeLayout)
            binding.shoeListingLayout.invalidate()
        }

        return binding.root
    }
}