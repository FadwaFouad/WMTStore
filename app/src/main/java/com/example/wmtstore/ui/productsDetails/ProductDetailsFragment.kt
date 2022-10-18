package com.example.wmtstore.ui.productsDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wmtstore.R
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.databinding.FragmentProductDetailsListDialogBinding
import com.example.wmtstore.ui.favourites.FavouritesViewModel

class ProductDetailsFragment(val product: Product) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productsDetailsViewModel: ProductsDetailsViewModel
    private lateinit var favouritesViewModel: FavouritesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productsDetailsViewModel = ViewModelProvider(this).get(ProductsDetailsViewModel::class.java)
        favouritesViewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)

        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //load image
        Glide.with(requireContext())
            .load(product.image)
            .into(binding.productImage)

        // show details
        binding.productName.text = "Name: ${product.name}"
        binding.productPrice.text = "Price : $${product.price}"
        binding.productSize.text = "Size: ${product.size}"
        binding.productSeller.text = "Seller: ${product.seller}"

        // add to card button
        binding.addToCartBtn.setOnClickListener {
            //save to cart
            productsDetailsViewModel.saveToCart(product)
            // alert user with it has added
            Toast.makeText(requireContext(), "Save to Cart", Toast.LENGTH_LONG).show()
            // close bottom sheet
            this.dismiss()
        }

        // check fav status of product
        if (favouritesViewModel.isFavorite(product.id!!))
            binding.selectAsFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        else
            binding.selectAsFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)

        // on click fav
        binding.selectAsFav.setOnClickListener {
            toggleFavIcon()
        }

    }

    private fun toggleFavIcon() {
        //get product id
        val id = product.id!!
        if (favouritesViewModel.isFavorite(id)) {
            favouritesViewModel.removeProduct(id)
            binding.selectAsFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            favouritesViewModel.addProduct(id)
            binding.selectAsFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)

        }
    }

}