package com.example.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Products
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }
}