package com.giannig.starwarskotlin.main

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.main.view.MainView

class MainPresenter(private val view: MainView) {

    private var task: AsyncTask<
            Void,
            Void,
            List<StarWarsSinglePlanet>
    >? = null

    fun update() {
        loadData()
        view.loading()
    }

    fun onClose() {
        task?.cancel(true)
    }

    class MyTask(var presenter: MainPresenter) : AsyncTask<Void, Void, List<StarWarsSinglePlanet>>() {
        override fun doInBackground(vararg params: Void?): List<StarWarsSinglePlanet>? {
            val planets = StarWarsDataProvider.providePlanets().execute()

            return if (planets.isSuccessful) {
                planets.body()?.planets
            } else {
                emptyList()
            }
        }

        override fun onPostExecute(result: List<StarWarsSinglePlanet>?) {
            super.onPostExecute(result)
            result?.let {
                presenter.updateUi(it)
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun loadData() {
        task = MyTask(this).execute()
    }

    fun updateUi(result: List<StarWarsSinglePlanet>) { //with context Main
        if (result.isEmpty()) {
            view.showErrorMessage()
        } else {
            view.updateList(result)
            view.showItemList()
        }
    }
}