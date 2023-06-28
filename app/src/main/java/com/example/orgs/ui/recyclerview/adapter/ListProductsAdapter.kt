package com.example.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.databinding.ProductsItemBinding
import com.example.orgs.model.Products
import java.text.NumberFormat
import java.util.Locale

class ListProductsAdapter(
    products: List<Products>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    private lateinit var mListener : RecyclerViewInterface
    interface RecyclerViewInterface {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : RecyclerViewInterface) {
        mListener = listener
    }

    private val products = products.toMutableList()

    class ViewHolder(val binding: ProductsItemBinding, listener: RecyclerViewInterface) : RecyclerView.ViewHolder(binding.root) {
        fun binding (products: Products) {
            binding.productItemTitleProduct.text = products.title_product
            binding.productItemDescription.text = products.description
            val brasilianCoinFormat : String = brasilianCoin(products)
            binding.productItemPrice.text = brasilianCoinFormat
            binding.productItemImage.load("https://images.pexels.com/photos/1055272/pexels-photo-1055272.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
        }

        private fun brasilianCoin(product: Products): String {
            val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return format.format(product.price)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(products[position])
        when (holder) {
            is ViewHolder -> holder.binding(products[position])
            else -> {}
        }
    }

    fun refresh(products: List<Products>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
