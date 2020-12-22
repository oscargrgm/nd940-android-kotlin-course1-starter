package com.udacity.shoestore.utils

import androidx.databinding.InverseMethod

object Converter {

    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String = value.toInt().toString()

    @JvmStatic
    fun stringToDouble(value: String): Double =
        if (value.isNotEmpty()) value.toDouble()
        else 0.toDouble()

}