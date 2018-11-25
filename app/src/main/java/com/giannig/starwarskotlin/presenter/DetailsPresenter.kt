package com.giannig.starwarskotlin.presenter

import com.giannig.starwarskotlin.model.StarWarsDataProvider
import com.giannig.starwarskotlin.model.State
import com.giannig.starwarskotlin.view.detailsview.DetailsActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailsPresenter(private val view: DetailsActivity) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun onClose() {
        job.cancel()
    }

    fun loadData(planetId: String) = launch {
        val response = StarWarsDataProvider.providePlanet(planetId)
        when (response) {
            is State.Planet -> updateUi(response)
            is State.Error -> view.showErrorMessage()
        }
    }

    private suspend fun updateUi(result: State.Planet) = withContext(Dispatchers.Main) {
        view.showData(result)
    }
}