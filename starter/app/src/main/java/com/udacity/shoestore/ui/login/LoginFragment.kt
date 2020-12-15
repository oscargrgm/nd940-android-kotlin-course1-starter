package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.createAccountBt.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            viewModel.onCreateAccountButtonClicked(email, password)
        }

        binding.loginBt.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            viewModel.onLoginButtonClicked(email, password)
        }

        viewModel.isNewAccount.observe(viewLifecycleOwner) { isNewAccount ->
            if (isNewAccount) {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.user)
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_email_already_registered),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.isLogged.observe(viewLifecycleOwner) { isLogged ->
            if (isLogged) {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.user)
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_invalid_credentials),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}