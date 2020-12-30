package com.udacity.shoestore.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.view.shoeList.ShoeListViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.eventLogged.observe(viewLifecycleOwner, Observer { loggedIn ->
            if (loggedIn)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.username.value?:"User"))
        })
        return binding.root
    }

}