package com.udacity.shoestore.view.shoeDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.model.Shoe

class ShoeDetailViewModel : ViewModel() {
    private val shoe = MutableLiveData<Shoe>()

}