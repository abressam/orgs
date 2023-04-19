package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Products

class ListProductsAdapter(
    private val context: Context,
    products: List<Products>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(product: Products) {
            val title_product = itemView.findViewById<TextView>(R.id.product_item_title_product)
            title_product.text = product.title_product

            val description = itemView.findViewById<TextView>(R.id.product_item_description)
            description.text = product.description

            val price = itemView.findViewById<TextView>(R.id.product_item_price)
            price.text = product.price.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.products_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincula(product)
    }

    fun refresh(products: List<Products>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
