package com.giannig.starwarskotlin.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.giannig.starwarskotlin.R
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.details.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsView {
    private val presenter = DetailsPresenter(this)

    companion object {
        const val PLANET_ID_EXTRA = "PLANET_ID_EXTRA"
        fun createIntent(context: Context): Intent = Intent(context, DetailsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val planetId = intent.getStringExtra(PLANET_ID_EXTRA)
        presenter.loadData(planetId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClose()
    }

    override fun loading() {
        detailsLoading.visibility = View.VISIBLE
        textDetailsPeople.visibility = View.GONE
        textDetailsPlanet.visibility = View.GONE
    }

    override fun showErrorMessage() {
        textDetailsPlanet.text = getString(R.string.error_details)
    }

    override fun showData(planet: StarWarsSinglePlanet) {
        detailsLoading.visibility = View.GONE
        textDetailsPeople.visibility = View.VISIBLE
        textDetailsPlanet.visibility = View.VISIBLE
        textDetailsPlanet.text = planet.name
        textDetailsPeople.text = planet.population
    }
}
