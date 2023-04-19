package com.example.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProductsItemBinding
import com.example.orgs.model.Products

class ListProductsAdapter(
    products: List<Products>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(val binding: ProductsItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(products[position]) {
                binding.productItemTitleProduct.text = title_product
                binding.productItemDescription.text = description
                binding.productItemPrice.text = price.toPlainString()
            }
        }

    }

    fun refresh(products: List<Products>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
