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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val nameField = findViewById<EditText>(R.id.name)
            val name = nameField.text.toString()

            val descriptionField = findViewById<EditText>(R.id.description)
            val description = descriptionField.text.toString()

            val priceField = findViewById<EditText>(R.id.price)
            val priceText = priceField.text.toString()

            val price = if (priceText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(priceText)
            }

            val newProduct = Products (

                title_product = name,
                description = description,
                price = price
                    )

            Log.i("ProductFormActivity", "onCreate: $newProduct")
            val dao = ProductsDao()
            dao.addProduct(newProduct)
            Log.i("ProductFormActivity", "onCreate: ${dao.listAll()}")
            finish()
        }
    }

}