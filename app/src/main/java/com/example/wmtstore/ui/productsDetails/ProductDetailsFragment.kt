package com.example.wmtstore.ui.productsDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.databinding.FragmentProductDetailsListDialogBinding

class ProductDetailsFragment(val product: Product) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productsDetailsViewModel: ProductsDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productsDetailsViewModel = ViewModelProvider(this).get(ProductsDetailsViewModel::class.java)
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
        }


    }


}