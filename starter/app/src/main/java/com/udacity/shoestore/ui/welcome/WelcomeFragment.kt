package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        val welcomeArguments by navArgs<WelcomeFragmentArgs>()

        viewModelFactory = WelcomeViewModelFactory(welcomeArguments.user)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory)
            .get(WelcomeViewModel::class.java)

        binding.welcomeViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}