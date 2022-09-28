package com.example.wmtstore.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wmtstore.R
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.ui.productsDetails.ProductDetailsFragment

class ProductsAdapter(
    val context: Context,
    val listOfproducts: List<Product>,
    val fragmentmanger : FragmentManager,
) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.layout_of_products, parent, false)
        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        var product = listOfproducts[position]
        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"

        //show image and load image url use glide
        Glide.with(context)
            .load(product.image)
            .into(holder.productImage)

        // set onclick listener on item
        holder.itemView.setOnClickListener {
            ProductDetailsFragment().show(fragmentmanger,"tag")
        }
    }

    override fun getItemCount(): Int {
        return listOfproducts.size
    }

}

class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productName = itemView.findViewById<TextView>(R.id.product_name)
    val productPrice = itemView.findViewById<TextView>(R.id.product_price)
    val productImage = itemView.findViewById<ImageView>(R.id.product_image)

}