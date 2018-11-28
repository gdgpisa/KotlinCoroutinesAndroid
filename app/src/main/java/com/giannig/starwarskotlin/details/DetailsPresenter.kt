package com.giannig.starwarskotlin.details

import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.State
import com.giannig.starwarskotlin.details.view.DetailsActivity
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
        val response = StarWarsDataProvider.provideSinglePlanet(planetId)
        when (response) {
            is State.Planet -> updateUi(response)
            is State.Error -> view.showErrorMessage()
        }
    }

    private suspend fun updateUi(result: State.Planet) = withContext(Dispatchers.Main) {
        view.showData(result)
    }
}