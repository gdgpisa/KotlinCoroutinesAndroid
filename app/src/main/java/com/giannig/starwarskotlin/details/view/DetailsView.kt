package com.giannig.starwarskotlin.details.view

import com.giannig.starwarskotlin.data.State

interface DetailsView {
    fun loading ()
    fun showErrorMessage()
    fun showData(result: State.Planet)
}