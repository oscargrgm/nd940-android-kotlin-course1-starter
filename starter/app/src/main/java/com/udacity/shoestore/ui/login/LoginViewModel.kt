package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.extensions.isNotEmptyNorBlank
import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {

    lateinit var user: User

    private val _isNewAccount = MutableLiveData<Boolean>()
    val isNewAccount: LiveData<Boolean>
        get() = _isNewAccount

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean>
        get() = _isLogged

    // TODO Add more restrictions to email validity
    fun onCreateAccountButtonClicked(email: String, password: String) {
        if (email.isNotEmptyNorBlank() && password.isNotEmptyNorBlank()) {
            user = User(email, password)
            _isNewAccount.value = true
        }
    }

    // TODO Add more restrictions to password validity
    fun onLoginButtonClicked(email: String, password: String) {
        if (email.isNotEmptyNorBlank() && password.isNotEmptyNorBlank()) {
            user = User(email, password)
            _isLogged.value = true
        }
    }
}