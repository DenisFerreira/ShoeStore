package com.udacity.shoestore.view.shoeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.model.Shoe
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeListAdapter(
    private val liveDataToObserve: LiveData<MutableList<Shoe>>,
    lifecycleOwner: LifecycleOwner,
    private val listener: ShoeItemListener
) :
    RecyclerView.Adapter<ShoeListAdapter.ViewHolder>() {

    private lateinit var binding: ShoeItemBinding

    init {
        liveDataToObserve.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }

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
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(listener.getContext()), R.layout.shoe_item, parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = liveDataToObserve.value?.get(position)
        holder.let {
            if (shoe != null) {
                it.name.text = shoe.name
                it.description.text = shoe.description
                it.company.text = shoe.company
                it.size.text = shoe.size.toString()
                it.itemView.setOnClickListener { listener.onClick(position) }
            }

        }

    }

    override fun getItemCount(): Int = liveDataToObserve.value?.size ?: 0
}