package com.giannig.starwarskotlin.main.view

import com.giannig.starwarskotlin.model.dto.StarWarsPlanet

interface MainView {
    fun loading ()
    fun updateList(list: List<StarWarsPlanet>)
    fun showItemList()
    fun showErrorMessage()
}