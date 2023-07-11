package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.databinding.ActivityListProductsBinding
import com.example.orgs.databinding.ActivityProductFormFieldsBinding
import com.example.orgs.ui.dialog.FormItemListDialog
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import java.math.BigDecimal

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
                val item = adapter.getItem(position)
                val title = item.title_product
                val description = item.description
                val price = item.price.toString()

                FormItemListDialog(this@ProductsListActivity, adapter)
                    .showItemListDialog(title, description, price, position)
            }
        })
    }
}