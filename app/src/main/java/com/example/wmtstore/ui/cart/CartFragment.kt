package com.example.wmtstore.ui.cart

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wmtstore.R
import com.example.wmtstore.databinding.FragmentCartBinding

class CartFragment : Fragment() {


    private var binding : FragmentCartBinding ?= null
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //add observe to RecycleView
        cartViewModel.getCarLiveData().observe(viewLifecycleOwner){
            initRecycleView ()
            // show Price On Checkout btn
            showPriceOnCheckout()
        }

        //Add line separator
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding!!.itemsInCard.addItemDecoration(dividerItemDecoration)


        // add listener to Checkout btn
        changeCheckoutBtn()

    }

    private fun initRecycleView (){
        binding!!.itemsInCard.layoutManager = LinearLayoutManager(requireContext())
        binding!!.itemsInCard.adapter = CartAdapter(requireContext(), cartViewModel)
    }
    private fun changeCheckoutBtn (){
        binding!!.checkoutBtn.setOnClickListener {
           val intent = Intent(requireContext(), CheckoutActivity::class.java)
            requireContext().startActivity(intent)
        }
    }
    private fun showPriceOnCheckout (){
        val price = cartViewModel.getTotalPrice()
        if (price == 0.0){
            binding!!.checkoutBtn.text = "Checkout"
            binding!!.checkoutBtn.isEnabled = false
        }
        else
            binding!!.checkoutBtn.text = "Checkout $price"
    }
}