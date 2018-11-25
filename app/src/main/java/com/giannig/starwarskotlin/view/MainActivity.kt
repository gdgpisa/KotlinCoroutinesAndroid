package com.giannig.starwarskotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.giannig.starwarskotlin.R
import com.giannig.starwarskotlin.model.dto.StarWarsPlanet
import com.giannig.starwarskotlin.presenter.MainPresenter
import com.giannig.starwarskotlin.view.adapters.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)
    private val adapter = MainListAdapter { presenter.onItemClick() }

    // retrofit
    // Kotlin coroutines
    // sealed class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemList.layoutManager = LinearLayoutManager(this)
        itemList.adapter = adapter
        presenter.onCreate()

        swipeToRefreshContainer.setOnRefreshListener {
            presenter.update()
        }

        swipeToRefreshContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

    }

    override fun updateList(list: List<StarWarsPlanet>) {
        adapter.addValues(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClose()
    }

    override fun loading() {
        swipeToRefreshContainer.isRefreshing = true
        itemList.visibility = View.GONE
        errorText.visibility = View.GONE
    }

    override fun showItemList() {
        swipeToRefreshContainer.isRefreshing = false
        itemList.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    override fun showErrorMessage() {
        swipeToRefreshContainer.isRefreshing = false
        itemList.visibility = View.GONE
        errorText.visibility = View.VISIBLE
    }
}

