package com.giannig.starwarskotlin.model.dto

data class StarWarsPlanet(
    val climate: String?,
    val created: String?,
    val diameter: String?,
    val edited: String?,
    val films: List<String>?,
    val gravity: String?,
    val name: String?,
    val orbitalPeriod: String?,
    val population: String?,
    val residents: List<String>?,
    val rotationPeriod: String?,
    val surfaceWater: String?,
    val terrain: String?,
    val url: String?
)