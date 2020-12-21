package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.extensions.isNotEmptyNorBlank
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class MainActivityViewModel : ViewModel() {

    lateinit var user: User

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean>
        get() = _isLogged

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    init {
        if (_shoeList.value == null) _shoeList.value = arrayListOf()
    }


    /**
     * This time, the functionality of creating a new account and login in is the same.
     */
    fun isValidUser(email: String, password: String) {
        if (email.isNotEmptyNorBlank() && password.isNotEmptyNorBlank()) {
            user = User(email, password)
            _isLogged.value = true
        }
    }

    fun isValidShoe(name: String, size: String, company: String, description: String) {
        if (name.isNotEmptyNorBlank() &&
                size.isNotEmptyNorBlank() &&
                company.isNotEmptyNorBlank() &&
                description.isNotEmptyNorBlank()
        ) {
            val shoe = Shoe(name, size.toDouble(), company, description)
            _shoeList.value?.add(shoe)
        }
    }
}