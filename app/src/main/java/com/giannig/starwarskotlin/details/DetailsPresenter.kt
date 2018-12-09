package com.giannig.starwarskotlin.details

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.giannig.starwarskotlin.data.StarWarsDataProvider
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.details.view.DetailsActivity

class DetailsPresenter(private val view: DetailsActivity) {


    private var asyncTask: AsyncTask<
        String,
        Void,
        StarWarsSinglePlanet?
    >? = null

    fun onClose() {
        this.asyncTask?.cancel(true)
    }

    fun loadData(planetId: String) {
        @SuppressLint("StaticFieldLeak")
        this.asyncTask = object : AsyncTask<String, Void, StarWarsSinglePlanet?>() {

            override fun doInBackground(vararg params: String?): StarWarsSinglePlanet? {
                val planet = StarWarsDataProvider.provideSinglePlanet(params[0] as String).execute()
                return if (planet.isSuccessful) {
                    planet.body()
                } else {
                    null
                }
            }

            override fun onPostExecute(result: StarWarsSinglePlanet?) {
                super.onPostExecute(result)
                result?.let {
                    updateUi(it)
                }
            }
        }.execute(planetId)
    }

    private fun updateUi(result: StarWarsSinglePlanet) { // withContext(Dispatchers.Main)
        view.showData(result)
    }
}