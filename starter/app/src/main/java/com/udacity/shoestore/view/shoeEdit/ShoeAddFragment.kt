package com.udacity.shoestore.view.shoeEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.model.Shoe
import com.udacity.shoestore.view.shoeList.ShoeListViewModel

class ShoeAddFragment : Fragment() {

    private val model: ShoeListViewModel by activityViewModels()
    private lateinit var binding: ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.shoe_detail_fragment, container, false)
        binding.shoe = Shoe()
        binding.saveShoeBtn.setOnClickListener {
            val shoe = binding.shoe!!
            if (shoe.name.isNotEmpty() and shoe.company.isNotEmpty() and shoe.description.isNotEmpty() and (shoe.size > 0)) {
                model.addShoe(shoe)
                findNavController().navigateUp()
            } else
                Toast.makeText(context, "Fill all fields to save register", Toast.LENGTH_SHORT)
                    .show()


        }
        binding.cancelShoeBtn.setOnClickListener { findNavController().navigateUp() }
        return binding.root
    }

}