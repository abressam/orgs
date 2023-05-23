package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.model.Products
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private lateinit var binding: ActivityProductFormBinding
    private val dao = ProductsDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configSaveButton()
        configCancelButton()
    }

    private fun configCancelButton() {
        binding.activityProductFormCancelButton.setOnClickListener {
            finish()
        }
    }

    private fun configSaveButton() {
        binding.activityProductFormSaveButton.setOnClickListener {
            val newProduct = createProduct()

            Log.i("ProductFormActivity", "onCreate: $newProduct")

            dao.addProduct(newProduct)
            Log.i("ProductFormActivity", "onCreate: ${dao.listAll()}")
            finish()
        }
    }

    private fun createProduct(): Products {
        val name = binding.activityProductFormTitleProduct.text.toString()
        val description = binding.activityProductFormDescription.text.toString()
        val priceText = binding.activityProductFormPrice.text.toString()

        val price = if (priceText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(priceText)
        }

        return Products(

            title_product = name,
            description = description,
            price = price
        )
    }

}