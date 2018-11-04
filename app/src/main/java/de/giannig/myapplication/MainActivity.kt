package de.giannig.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

    override fun updateList(list: List<String>) {
        adapter.addValues(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClose()
    }

    override fun loading() {
        loadingBar.visibility = View.VISIBLE
        itemList.visibility = View.GONE
        errorText.visibility = View.GONE
    }

    override fun showItemList() {
        loadingBar.visibility = View.GONE
        itemList.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    override fun showErrorMessage() {
        loadingBar.visibility = View.GONE
        itemList.visibility = View.GONE
        errorText.visibility = View.VISIBLE
    }
}

