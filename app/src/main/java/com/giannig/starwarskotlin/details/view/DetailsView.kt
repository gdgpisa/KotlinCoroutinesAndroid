package com.giannig.starwarskotlin.details.view

import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet

interface DetailsView {
    fun loading()
    fun showErrorMessage()
    fun showData(planet: StarWarsSinglePlanet)
}