package com.example.orgs.dao

import com.example.orgs.model.Products
import java.math.BigDecimal

class ProductsDao {

    fun addProduct(product: Products) {
        products.add(product)
    }

    fun listAll() : List<Products> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Products>(
            Products(
                title_product = "Salada de frutas",
                description = "Uva, banana e kiwi",
                price = BigDecimal("25.99")
            )
        )
    }
}