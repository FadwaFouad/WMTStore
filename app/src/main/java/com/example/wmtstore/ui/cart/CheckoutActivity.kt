package com.example.wmtstore.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.wmtstore.R
import com.example.wmtstore.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set binding
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init view model
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        // Calculate subtotal , shipping cost and total
        showOrderInfo()

        // setup spinner
        setupSpinner()

        // show payment and check inputs
        binding.paymentBtn.setOnClickListener {
            if (inputIsValid()) {
                showConfirmationDialog()
                cartViewModel.clearCart()
            }
        }
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setView(R.layout.layout_payment_successful)
            .setNegativeButton("OK") { _, _ ->
                this@CheckoutActivity.finish()
            }
            .show()
    }

    private fun inputIsValid(): Boolean {
        val cardNumber = binding.cardNumber.text.toString()
        val cvv = binding.cvv.text.toString()

        if (cardNumber.length < 13) {
            Toast.makeText(this, "Invalid Card Number", Toast.LENGTH_LONG).show()
            return false
        } else if (cardNumber.length > 15) {
            Toast.makeText(this, "Invalid Card Number", Toast.LENGTH_LONG).show()
            return false
        } else if (cvv.length != 3) {
            Toast.makeText(this, "Invalid CVV", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun setupSpinner() {
        val cardTypesList: List<String> = listOf("Visa Card", "Master Card", "PayPal")
        binding.cardTypes.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            cardTypesList,
        )
    }

    private fun showOrderInfo() {
        val totalPrice = cartViewModel.getTotalPrice()
        val shippingPrice = totalPrice * 0.01

        binding.subtotal.text = "$$totalPrice"
        binding.shippingCost.text = "$$shippingPrice"
        binding.total.text = "$${totalPrice + shippingPrice}"
    }
}