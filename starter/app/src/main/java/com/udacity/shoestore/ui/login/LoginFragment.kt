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

        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            createAccountBt.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passEt.text.toString()
                viewModel.onCreateAccountButtonClicked(email, password)
            }

            loginBt.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passEt.text.toString()
                viewModel.onLoginButtonClicked(email, password)
            }
        }

        with(viewModel) {
            isNewAccount.observe(viewLifecycleOwner) { isNewAccount ->
                if (isNewAccount) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(user)
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        "The email is already registered.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            isLogged.observe(viewLifecycleOwner) { isLogged ->
                if (isLogged) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(user)
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email or password is incorrect.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return binding.root
    }
}