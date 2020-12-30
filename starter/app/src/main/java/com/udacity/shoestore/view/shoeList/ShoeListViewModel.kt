package com.udacity.shoestore.view.shoeList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.model.Shoe

class ShoeListViewModel() : ViewModel() {
    fun updateShoe(shoe: Shoe?, position: Int) {
        if (shoe != null) {
            _shoes.value?.set(position, shoe)
        }
    }

    // TODO: Implement the ViewModel
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf(
            Shoe("Tenis", "Nike", 1, "teste"),
            Shoe("Sandália", "Adidas", 2, "teste"),
            Shoe("Chuteira", "Adidas", 3, "teste"),
            Shoe("Sapatenis", "Puma", 4, "teste"),
            Shoe("Sapato", "Mizuno", 5, "teste"),
            Shoe("Bota", "Rainha", 6, "teste")
        )
    }
}