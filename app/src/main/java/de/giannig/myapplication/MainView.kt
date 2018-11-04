package de.giannig.myapplication

interface MainView {
    fun loading ()
    fun updateList(list: List<String>)
    fun showItemList()
    fun showErrorMessage()
}