package com.giannig.starwarskotlin.main

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.main.view.MainView

class MainPresenter(private val view: MainView) {

    @SuppressLint("StaticFieldLeak")
    private val task = object : AsyncTask<Void, Void, List<StarWarsSinglePlanet>>() {
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
                updateUi(it)
            }
        }
    }

    fun update() {
        loadData()
        view.loading()
    }

    fun onClose() {
        task.cancel(true)
    }

    private fun loadData() {
        task.execute()
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