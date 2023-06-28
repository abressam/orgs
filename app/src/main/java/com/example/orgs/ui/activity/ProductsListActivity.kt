package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.databinding.ActivityListProductsBinding
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import com.google.android.material.textfield.TextInputEditText

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
        binding.extendedFab.setOnClickListener{
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

        adapter.setOnItemClickListener(object : ListProductsAdapter.RecyclerViewInterface {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@ProductsListActivity, "You clicked in $position!", Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this@ProductsListActivity)
                    .setMessage("Altere os dados do produto como desejar")
                    .setTitle("Produto selecionado")
                    .setView(R.layout.activity_product_form_fields)
                    .setPositiveButton("Salvar") { _, _ -> }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()
            }
        })
    }
}