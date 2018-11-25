package com.giannig.starwarskotlin.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.giannig.starwarskotlin.R
import com.giannig.starwarskotlin.data.dto.StarWarsPlanet
import com.giannig.starwarskotlin.main.MainPresenter
import com.giannig.starwarskotlin.details.view.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)
    private val adapter = MainListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.setClickLister { onClickItem(it) }
        itemList.layoutManager = LinearLayoutManager(this)
        itemList.adapter = adapter
        presenter.update()
        setUpSwipeToRefresh()
    }

    private fun onClickItem(clickedId :Int){
        startActivity(DetailsActivity.createIntent(this).apply {
            putExtra(DetailsActivity.PLANET_ID_EXTRA, (clickedId+2).toString())
        })
    }

    private fun setUpSwipeToRefresh() {
        swipeToRefreshContainer.setOnRefreshListener { presenter.update() }

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
