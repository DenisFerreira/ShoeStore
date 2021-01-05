package com.udacity.shoestore.view.shoeList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding.inflate
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.view.login.LoginViewModel

class ShoeListFragment : Fragment(), ShoeListAdapter.ShoeItemListener {

    private lateinit var binding: ShoeListFragmentBinding

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val model: ShoeListViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.shoe_list_fragment, container, false)
        binding.shoeListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.shoeListRecyclerView.adapter =
            ShoeListAdapter(model.shoes, viewLifecycleOwner, this)
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeAddFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onClick(position: Int) {
        findNavController().navigate(
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(
                position
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_menu_item -> {
                loginViewModel.logout()
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
            else -> null
        }
        return super.onOptionsItemSelected(item)
    }

}