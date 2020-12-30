package com.udacity.shoestore.view.shoeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.model.Shoe
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeListAdapter(var shoesList: List<Shoe>, private val listener: ShoeItemListener) :
    RecyclerView.Adapter<ShoeListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.shoe_name_textView
        val description = itemView.shoe_description_text_view
        val company = itemView.shoe_company_text_view
        val size = itemView.shoe_size_text_view
    }

    interface ShoeItemListener {
        fun getContext(): Context?
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(listener.getContext()).inflate(R.layout.shoe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = shoesList[position]
        holder.let {
            it.name.text = shoe.name
            it.description.text = shoe.description
            it.company.text = shoe.company
            it.size.text = shoe.size.toString()
            it.itemView.setOnClickListener { listener.onClick(position) }
        }

    }

    override fun getItemCount(): Int = shoesList.size
}