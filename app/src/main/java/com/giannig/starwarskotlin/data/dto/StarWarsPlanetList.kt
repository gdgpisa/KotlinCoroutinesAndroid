package com.giannig.starwarskotlin.data.dto

import com.google.gson.annotations.SerializedName

data class StarWarsPlanetList(

    @SerializedName("results")
    val planets: List<StarWarsSinglePlanet>?
)