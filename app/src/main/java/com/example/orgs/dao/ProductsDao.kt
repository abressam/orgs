package com.example.orgs.dao

import com.example.orgs.model.Products

class ProductsDao {

    fun addProduct(product: Products) {
        products.add(product)
    }

    fun listAll() : List<Products> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Products>()
    }
}