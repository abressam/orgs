package com.example.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.databinding.ProductsItemBinding
import com.example.orgs.extensions.tryLoadImage
import com.example.orgs.model.Products
import java.math.BigDecimal
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

            val visible = if(products.image != null) {
                View.VISIBLE
            } else {
                View.GONE // não mostra o conteúdo
            }

            binding.productItemImage.visibility = visible

            binding.productItemImage.tryLoadImage(products.image)
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

    fun delete(position: Int) {
        this.products.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, title: String, description: String, price: BigDecimal) {
        products[position].title_product = title
        products[position].description = description
        products[position].price = price
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Products {
        return products[position]
    }

}
