package com.udacity.shoestore.view.shoeEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.view.shoeList.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private val model: ShoeListViewModel by activityViewModels()
    private lateinit var binding: ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shoeDetailFragmentArgs by navArgs<ShoeDetailFragmentArgs>()
        val position = shoeDetailFragmentArgs.shoeId

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.shoe_detail_fragment, container, false)
        model.shoes.observe(viewLifecycleOwner, Observer { binding.shoe = it.get(position) })

        binding.saveShoeBtn.setOnClickListener {
            model.updateShoe(binding.shoe, position)
            findNavController().navigateUp()
        }
        binding.cancelShoeBtn.setOnClickListener { findNavController().navigateUp() }
        return binding.root
    }

}