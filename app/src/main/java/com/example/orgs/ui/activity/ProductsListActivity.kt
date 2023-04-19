package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListActivity : AppCompatActivity(R.layout.activity_list_products) {

    private val dao = ProductsDao()
    private val adapter = ListProductsAdapter(this, products = dao.listAll())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh(dao.listAll())
    }

    private fun configFab() {
        val fab = findViewById<FloatingActionButton>(R.id.activity_list_products_floatingActionButton)
        fab.setOnClickListener {
            goToProductsForm()
        }
    }

    private fun goToProductsForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_list_products_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}