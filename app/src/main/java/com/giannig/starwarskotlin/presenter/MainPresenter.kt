package com.giannig.starwarskotlin.presenter

import com.giannig.starwarskotlin.model.dto.StarWarsPlanet
import com.giannig.starwarskotlin.model.StarWarsDataProvider
import com.giannig.starwarskotlin.view.MainView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: MainView) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + IO

    fun onItemClick() {

    }

    fun onCreate() {
        loadData()
        view.loading()
    }

    fun update() {
        loadData()
        view.loading()
    }

    fun onClose() {
        job.cancel()
    }

    private fun loadData() = launch(Main) {
        val result = StarWarsDataProvider().providePlanets().await()
        result.results?.let { updateUi(it) } ?: updateUi(emptyList())
    }

    private suspend fun updateUi(result: List<StarWarsPlanet>) = withContext(Main) {
        view.updateList(result)
        view.showItemList()
    }
}