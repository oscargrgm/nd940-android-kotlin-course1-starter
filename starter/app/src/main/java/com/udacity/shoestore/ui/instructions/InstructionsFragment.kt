package com.udacity.shoestore.ui.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instructions,
            container,
            false
        )
        binding.instructionsNextBt.setOnClickListener { onNextButtonClicked() }
        return binding.root
    }

    private fun onNextButtonClicked() {
        findNavController().navigate(
            InstructionsFragmentDirections.actionInstructionsFragmentToShoeListingFragment()
        )
    }
}