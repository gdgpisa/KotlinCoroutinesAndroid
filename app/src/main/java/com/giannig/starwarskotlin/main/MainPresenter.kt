package com.giannig.starwarskotlin.main

import com.giannig.starwarskotlin.model.dto.StarWarsPlanet
import com.giannig.starwarskotlin.model.StarWarsDataProvider
import com.giannig.starwarskotlin.model.State
import com.giannig.starwarskotlin.main.view.MainView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
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
        when (response) {
            is State.PlanetList -> response.result?.let { updateUi(it) }
            is State.Error -> view.showErrorMessage()
        }
    }

    private suspend fun updateUi(result: List<StarWarsPlanet>) = withContext(Main) {
        view.updateList(result)
        view.showItemList()
    }
}