package com.udacity.shoestore.ui.shoedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.extensions.isNotEmptyNorBlank
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel : ViewModel() {

    var shoe: Shoe = Shoe("", 0.0, "", "", listOf())

    private val _isShoeAdded = MutableLiveData<Boolean>()
    val isShoeAdded: LiveData<Boolean>
        get() = _isShoeAdded

    fun onAcceptButtonClicked() {
        if (shoe.name.isNotEmptyNorBlank() &&
            shoe.getSize().toString().isNotEmptyNorBlank() &&
            shoe.company.isNotEmptyNorBlank() &&
            shoe.description.isNotEmptyNorBlank()
        ) {
            _isShoeAdded.value = true
        }
    }
}