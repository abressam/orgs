package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.model.Products
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val dao = ProductsDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSaveButton()
    }

    private fun configSaveButton() {
        val saveButton = findViewById<Button>(R.id.activity_product_form_save_button)
        saveButton.setOnClickListener {
            val newProduct = createProduct()

            Log.i("ProductFormActivity", "onCreate: $newProduct")

            dao.addProduct(newProduct)
            Log.i("ProductFormActivity", "onCreate: ${dao.listAll()}")
            finish()
        }
    }

    private fun createProduct(): Products {
        val nameField = findViewById<EditText>(R.id.activity_product_form_title_product)
        val name = nameField.text.toString()

        val descriptionField = findViewById<EditText>(R.id.activity_product_form_description)
        val description = descriptionField.text.toString()

        val priceField = findViewById<EditText>(R.id.activity_product_form_price)
        val priceText = priceField.text.toString()

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