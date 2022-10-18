package com.example.wmtstore.ui.favourites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wmtstore.R
import com.example.wmtstore.databinding.FragmentFavoritesBinding
import com.example.wmtstore.ui.home.ProductsAdapter

class FavouritesFragment : Fragment() {


    private lateinit var viewModel: FavouritesViewModel
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getAllFavorites().observe(viewLifecycleOwner) { listOfIDs ->
            viewModel.getProductFromIds(listOfIDs).observe(viewLifecycleOwner) { listOfProducts ->
                if (listOfProducts.isNotEmpty()) {
                    binding.listOfFav.visibility = View.VISIBLE
                    binding.notFound.visibility = View.GONE

                    // set adapter
                    binding.listOfFav.adapter =
                        ProductsAdapter(requireContext(), listOfProducts, childFragmentManager)
                    binding.listOfFav.layoutManager = GridLayoutManager(requireContext(), 2)
                } else {
                    // no fav products
                    binding.listOfFav.visibility = View.GONE
                    binding.notFound.visibility = View.VISIBLE
                }


            }
        }
    }
}