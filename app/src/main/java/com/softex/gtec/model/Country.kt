package com.softex.gtec.model

data class Country(
    val ID: Int,
    val Name: String
) {
    var cities = mutableListOf<City?>()
}