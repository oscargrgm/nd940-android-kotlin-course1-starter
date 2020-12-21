package com.udacity.shoestore.ui.shoelisting

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.ui.MainActivity

class ShoeListingFragment : Fragment() {

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

        val viewModel = (activity as MainActivity).viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.shoeListingFab.setOnClickListener {
            findNavController().navigate(
                ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
            )
        }

        viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            shoeList.forEach { shoe ->
                val shoeLayout = layoutInflater.inflate(R.layout.layout_shoe, null, true)
                shoeLayout.run {
                    with(shoe) {
                        findViewById<TextView>(R.id.shoe_item_name_tv).text = name
                        findViewById<TextView>(R.id.shoe_item_company_tv).text = company
                        findViewById<TextView>(R.id.shoe_item_size_tv).text = size.toString()
                        findViewById<TextView>(R.id.shoe_item_description_tv).text = description
                    }
                }

                binding.shoeListingLl.addView(shoeLayout)
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
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || return super.onOptionsItemSelected(item)
    }
}