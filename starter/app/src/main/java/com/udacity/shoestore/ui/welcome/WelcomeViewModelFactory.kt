package com.udacity.shoestore.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.User

class WelcomeViewModelFactory(private val userInfo: User) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(userInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}