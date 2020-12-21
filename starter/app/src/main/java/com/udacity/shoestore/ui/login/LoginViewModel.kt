package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.extensions.isNotEmptyNorBlank
import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createAccount(email: String, password: String) {
        if (email.isNotEmptyNorBlank() && password.isNotEmptyNorBlank()) {
            _user.value = User(email, password)
        }
    }

    fun login(email: String, password: String) {
        if (email.isNotEmptyNorBlank() && password.isNotEmptyNorBlank()) {
            _user.value = User(email, password)
        }
    }
}