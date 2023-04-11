package com.example.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Products
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListProductsAdapter(this, products = listOf(
            Products(
                title_product = "teste",
                description = "teste teste",
                price = BigDecimal("19.99")
            ),
            Products(
                title_product = "teste 1",
                description = "teste teste 1",
                price = BigDecimal("14.99")
            )
        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}