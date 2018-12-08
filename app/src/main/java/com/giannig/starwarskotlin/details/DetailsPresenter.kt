package com.giannig.starwarskotlin.details

import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
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
        updateUi(response)
    }

    private suspend fun updateUi(result: StarWarsSinglePlanet) = withContext(Dispatchers.Main) {
        view.showData(result)
    }
}