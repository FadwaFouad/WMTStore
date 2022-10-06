package com.example.wmtstore.ui.cart

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
        }

        //Add line separator
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding!!.itemsInCard.addItemDecoration(dividerItemDecoration)

        // add listener to checkout Btn
        changeCheckoutBtn ()

    }

    private fun initRecycleView (){
        binding!!.itemsInCard.layoutManager = LinearLayoutManager(requireContext())
        binding!!.itemsInCard.adapter = CartAdapter(requireContext(), cartViewModel)
    }
    private fun changeCheckoutBtn (){
        binding!!.checkoutBtn.setOnClickListener {
            val price = cartViewModel.getTotalPrice()
            if (price == 0.0)
                binding!!.checkoutBtn.text = "Checkout"
            else
            binding!!.checkoutBtn.text = "Checkout $price"
        }
    }
}