package com.giannig.starwarskotlin.view.detailsview

import com.giannig.starwarskotlin.model.State

interface DetailsView {
    fun loading ()
    fun showErrorMessage()
    fun showData(result: State.Planet)
}