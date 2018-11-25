package com.giannig.starwarskotlin.model.dto

data class StarWarsPeople(
    val birthYear: String?,
    val created: String?,
    val edited: String?,
    val eyeColor: String?,
    val films: List<String>?,
    val gender: String?,
    val hairColor: String?,
    val height: String?,
    val homeWorld: String?,
    val mass: String?,
    val name: String?,
    val skinColor: String?,
    val species: List<String>?,
    val starShips: List<String>?,
    val url: String?,
    val vehicles: List<String>?
)