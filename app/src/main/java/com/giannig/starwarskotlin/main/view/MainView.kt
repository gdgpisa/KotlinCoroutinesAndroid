package com.giannig.starwarskotlin.main.view

import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet

interface MainView {
    fun loading ()
    fun updateList(list: List<StarWarsSinglePlanet>)
    fun showItemList()
    fun showErrorMessage()
}