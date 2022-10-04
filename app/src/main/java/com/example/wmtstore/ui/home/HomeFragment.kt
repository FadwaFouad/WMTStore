package com.example.wmtstore.ui.home

import android.os.Bundle
import android.view.*
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wmtstore.R
import com.example.wmtstore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private  lateinit var   homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // add menu to home page
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(HomeMenuProvider(), viewLifecycleOwner, Lifecycle.State.RESUMED)

        //Listen for products
        homeViewModel.getAllProducts().observe(viewLifecycleOwner){ listOfProducts ->
            binding.listOfProducts.layoutManager= GridLayoutManager(requireContext(), 2,)
            binding.listOfProducts.adapter= ProductsAdapter(requireContext(), listOfProducts, childFragmentManager)
        }
    }
}

// class help to add menu to fragment
class HomeMenuProvider() : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_menu, menu)
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }
}