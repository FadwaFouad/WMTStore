package com.example.wmtstore.ui.cart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.wmtstore.R
import com.example.wmtstore.data.models.Notification
import com.example.wmtstore.databinding.ActivityCheckoutBinding
import com.example.wmtstore.ui.notifications.NotificationsViewModel

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var notificationViewModel: NotificationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set binding
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init view model
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        notificationViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        // Calculate subtotal , shipping cost and total
        showOrderInfo()

        // setup spinner
        setupSpinner()

        // show payment and check inputs
        binding.paymentBtn.setOnClickListener {
            if (inputIsValid()) {
                showConfirmationDialog()
                showNotification(
                    message = "You just paid $${cartViewModel.getTotalPrice()}"
                )
                cartViewModel.clearCart()
            }
        }
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setView(R.layout.layout_payment_successful)
            .setPositiveButton("OK") { _, _ ->
                this@CheckoutActivity.finish()
            }
            .setCancelable(false)
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

    // show user payment notification
    private fun showNotification(message : String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // declare variables
            val channelName: String = "Payment Confirmation"
            val description: String = "User just make a successful payment"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val id = "Payment_Successful"

            //create channel
            val notificationChannel = NotificationChannel(id, channelName, importance)
            notificationChannel.description = description

            // assign channel to device notification manger
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            // create notification builder
            val builder = NotificationCompat.Builder(this, id)
            builder.setSmallIcon(R.mipmap.ic_launcher)
            builder.setContentTitle("Payment Done")
            builder.setContentText(message)

            //show notification
            val notificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManagerCompat.notify(12333, builder.build())

            // save notification
            saveNotification(message)

        }
    }

    private fun saveNotification(message: String) {
        val notification = Notification(System.currentTimeMillis(),message)
        notificationViewModel.saveNotification(notification)
    }
}