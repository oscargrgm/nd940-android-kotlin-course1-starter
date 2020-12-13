package com.udacity.shoestore.extensions

fun String.isNotEmptyNorBlank() = this.isNotEmpty() && this.isNotBlank()