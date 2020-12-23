package com.udacity.shoestore.ui.shoelisting

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.databinding.LayoutShoeBinding
import com.udacity.shoestore.ui.MainActivity
import com.udacity.shoestore.ui.MainActivityViewModel

class ShoeListingFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel

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

        viewModel = (activity as MainActivity).viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeListingFab.setOnClickListener {
            findNavController().navigate(
                ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
            )
        }

        viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            shoeList.forEach { shoe ->
                DataBindingUtil.inflate<LayoutShoeBinding>(
                    layoutInflater,
                    R.layout.layout_shoe,
                    null,
                    true
                ).run {
                    with(shoe) {
                        shoeItemNameTv.text = name
                        shoeItemCompanyTv.text = company
                        shoeItemSizeTv.text = getSize().toString()
                        shoeItemDescriptionTv.text = description
                    }
                    binding.shoeListingLl.addView(root)
                }
            }
            binding.shoeListingLl.invalidate()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_logout -> {
                // Clear user information and shoes list
                viewModel.user = null
                viewModel.shoeList.value?.clear()

                findNavController().navigate(
                    ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment()
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}