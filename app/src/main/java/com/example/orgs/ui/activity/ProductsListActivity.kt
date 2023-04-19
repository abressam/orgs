package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.databinding.ActivityListProductsBinding
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter

class ProductsListActivity : AppCompatActivity(R.layout.activity_list_products) {

    private lateinit var binding: ActivityListProductsBinding

    private val dao = ProductsDao()
    private val adapter = ListProductsAdapter(products = dao.listAll())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh(dao.listAll())
    }

    private fun configFab() {
        binding.activityListProductsFloatingActionButton.setOnClickListener {
            goToProductsForm()
        }
    }

    private fun goToProductsForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configRecyclerView() {
        binding.activityListProductsRecyclerView.adapter = adapter
        binding.activityListProductsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}