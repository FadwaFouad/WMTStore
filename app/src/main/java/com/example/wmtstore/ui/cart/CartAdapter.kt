package com.example.wmtstore.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wmtstore.R
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.CartRepository
import com.google.android.material.button.MaterialButton

class CartAdapter(val context: Context, val cartViewModel: CartViewModel) :
    RecyclerView.Adapter<CartViewHolder>() {
    val listOfSelectedProducts: List<Product> = cartViewModel.getProducts()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = listOfSelectedProducts[position]
        val qty = cartViewModel.getQuantity(product)

        // show name and price of product
        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"
        // load image of product
        Glide.with(context).load(product.image).into(holder.image)
        // show quantity
        holder.quantity.text = qty.toString()
        // increase and decrease qty of product
        holder.increaseQty.setOnClickListener {
            cartViewModel.increaseQuantity(product)
        }

        holder.decreaseQty.setOnClickListener {
            if (qty > 1)
                cartViewModel.decreaseQuantity(product)
            else
                holder.decreaseQty.isEnabled = false
        }
        holder.delete.setOnClickListener {
            cartViewModel.deleteProduct(product)
        }

    }

    override fun getItemCount(): Int = listOfSelectedProducts.size
}

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //add new comment
    val image: ImageView = itemView.findViewById(R.id.product_image)
    val productName: TextView = itemView.findViewById(R.id.product_name)
    val productPrice: TextView = itemView.findViewById(R.id.product_price)
    val quantity: TextView = itemView.findViewById(R.id.qty)
    val increaseQty: MaterialButton = itemView.findViewById(R.id.increaseQty)
    val decreaseQty: MaterialButton = itemView.findViewById(R.id.decreaseQty)
    val delete: MaterialButton = itemView.findViewById(R.id.delete)


}