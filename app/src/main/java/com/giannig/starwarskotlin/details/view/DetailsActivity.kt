package com.giannig.starwarskotlin.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.*
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
        planetImageView.setImageDrawable(getDrawable(R.drawable.empire))
        planetImageView.visibility = GONE
        hideTextViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClose()
    }

    override fun loading() {
        detailsLoading.visibility = VISIBLE
        planetImageView.visibility = GONE
        hideTextViews()
    }

    private fun hideTextViews() {
        textPlanetCitizens.visibility = GONE
        textDetailsPlanetName.visibility = GONE
        textPlanetDimensions.visibility = GONE
        textPlanetSurface.visibility = GONE
    }

    override fun showErrorMessage() {
        textDetailsPlanetName.text = getString(R.string.error_details)
    }

    override fun showData(planet: StarWarsSinglePlanet) {
        detailsLoading.visibility = GONE
        planetImageView.visibility = VISIBLE

        planet.run {
            textDetailsPlanetName.text = name
            textPlanetCitizens.text = getString(
                R.string.population,
                population
            )

            textPlanetDimensions.text = getString(
                R.string.planet_dimensions,
                diameter
            )

            textPlanetSurface.text = getString(
                R.string.terrain_and_water,
                terrain ?: "no terrain",
                surfaceWater ?: "no water"
            )
        }

        showTextViews()
    }

    private fun showTextViews() {
        textPlanetCitizens.visibility = VISIBLE
        textDetailsPlanetName.visibility = VISIBLE
        textPlanetDimensions.visibility = VISIBLE
        textPlanetSurface.visibility = VISIBLE
    }
}
