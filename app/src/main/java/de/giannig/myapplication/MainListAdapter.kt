package de.giannig.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//todo 3: replace String with dto
//todo 4: add model
class MainListAdapter(private val onItemClick: (View) -> Unit) : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>(){

   private var list:List<String> = emptyList()

    fun addValues(newList: List<String>) {
        list = list.plus(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val ctx = parent.context
        val inflate = LayoutInflater.from(ctx)
        val v = inflate.inflate(R.layout.main_list_item, parent, false)
        return MainListViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val item = list[position]
        holder.set(item, onItemClick)
    }

    class MainListViewHolder (private val v : View): RecyclerView.ViewHolder(v) {
        private val imageView = v.findViewById<ImageView>(R.id.imageItem)
        private val textView = v.findViewById<TextView>(R.id.textItem)

        fun set(item: String, onClick: (View) -> Unit) {
            textView.text = item
            v.setOnClickListener{onClick(it)}
        }
    }
}