package com.udacity.shoestore.view.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModelFactory: WelcomeViewModelFactory

    private lateinit var binding: WelcomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val welcomeFragmentArgs by navArgs<WelcomeFragmentArgs>()
        viewModelFactory = WelcomeViewModelFactory(welcomeFragmentArgs.userName)
        val viewModel: WelcomeViewModel by viewModels { viewModelFactory }

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.welcome_fragment, container, false)
        binding.btnGoToInstructions.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}