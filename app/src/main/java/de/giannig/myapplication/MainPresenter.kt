package de.giannig.myapplication

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

    fun onClose() {
        job.cancel()
    }

    private fun loadData() = launch {
        val result = async {
            //todo 0: add tests
            //todo 1: remove mocked list+delay and replace with retrofit2 + coroutine support
            //todo 2: add sealed class state
            delay(1000)
            listOf("Kotlin", "Java")
        }.await()

        if (result.isNotEmpty()) {
            updateUi(result)
        }
    }

    private suspend fun updateUi(result: List<String>) = withContext(Main) {
        view.updateList(result)
        view.showItemList()
    }
}