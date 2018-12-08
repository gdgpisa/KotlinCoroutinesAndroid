package com.giannig.starwarskotlin.main

import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.main.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: MainView) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + IO

    fun update() {
        loadData()
        view.loading()
    }

    fun onClose() {
        job.cancel()
    }

    private fun loadData() = launch {
        val response = StarWarsDataProvider.providePlanets()
        val planets = response.planets ?: emptyList()
        updateUi(planets)
    }

    private suspend fun updateUi(result: List<StarWarsSinglePlanet>) = withContext(Main) {
        if (result.isEmpty()) {
            view.showErrorMessage()
        } else {
            view.updateList(result)
            view.showItemList()
        }
    }
}