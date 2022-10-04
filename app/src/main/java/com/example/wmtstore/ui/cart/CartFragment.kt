package com.example.wmtstore.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wmtstore.R
import com.example.wmtstore.databinding.FragmentCartBinding

class CartFragment : Fragment() {


    private  var binding : FragmentCartBinding? = null
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        binding= FragmentCartBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var productsInCart = cartViewModel.getProducts()
        Toast.makeText(requireContext(), "${productsInCart.size} in cart", Toast.LENGTH_LONG).show()
    }
}