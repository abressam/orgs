package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val nameField = findViewById<EditText>(R.id.name)
            val name = nameField.text.toString()
            Log.i("ProductFormActivity", "onCreate: $name")
        }
    }

}