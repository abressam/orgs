package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.extensions.tryLoadImage
import com.example.orgs.model.Products
import com.example.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private lateinit var binding: ActivityProductFormBinding
    private val dao = ProductsDao()
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = "Cadastrar produto"
        configSaveButton()
//        enableSaveButton()
        configCancelButton()

        binding.activityProductImage.setOnClickListener {
            FormImageDialog(this)
                .showDialog(url) { image ->
                url = image
                binding.activityProductImage.tryLoadImage(url)
                }
        }

        errorMessage()
    }

    private fun errorMessage() {
        val title_product = binding.activityProductFormTitleProduct.text.toString()

        binding.activityProductFormTitleProduct.setOnFocusChangeListener { _, focused ->
            if(!focused && title_product.isEmpty())
                binding.activityProductFormTextInputTitle.error = "Campo não pode ser vazio"
            else
                binding.activityProductFormTextInputTitle.error = null

        }
//        binding.activityProductFormTitleProduct.doOnTextChanged { text, start, before, count ->
//            if(text!!.isNotEmpty())
//                binding.activityProductFormTextInputTitle.error = null
//            else
//                binding.activityProductFormTextInputTitle.error = "Campo não pode ser vazio"
//        }
    }

    private fun configCancelButton() {
        binding.activityProductFormCancelButton.setOnClickListener {
            finish()
        }
    }

    private fun configSaveButton() {
        binding.activityProductFormSaveButton.setOnClickListener {
            val newProduct = createProduct()
            dao.addProduct(newProduct)
            finish()
        }
    }

    private fun createProduct(): Products {
        val title_product = binding.activityProductFormTitleProduct.text.toString()
        val description = binding.activityProductFormDescription.text.toString()
        val priceText = binding.activityProductFormPrice.text.toString()

        val price = if (priceText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(priceText)
        }

        return Products(
            title_product = title_product,
            description = description,
            price = price,
            image = url
        )
    }

}