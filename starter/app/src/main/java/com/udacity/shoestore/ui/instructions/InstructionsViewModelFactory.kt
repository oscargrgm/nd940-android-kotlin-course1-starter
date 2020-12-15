package com.udacity.shoestore.ui.instructions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.User
import com.udacity.shoestore.ui.welcome.WelcomeViewModel

class InstructionsViewModelFactory(private val userInfo: User) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InstructionsViewModel::class.java)) {
            return WelcomeViewModel(userInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}