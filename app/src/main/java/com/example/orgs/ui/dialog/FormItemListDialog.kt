package com.example.orgs.ui.dialog

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.ActivityProductFormFieldsBinding
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import java.math.BigDecimal

class FormItemListDialog(private val context: Context, private val adapter: ListProductsAdapter) {
    fun showItemListDialog(
        titleDefault: String? = null,
        descriptionDefault: String? = null,
        priceDefault: String? = null,
        position: Int
        ) {
        ActivityProductFormFieldsBinding
            .inflate(LayoutInflater.from(context)).apply {

                titleDefault.let { activityProductFormTitleProduct.setText(it) }
                descriptionDefault.let { activityProductFormDescription.setText(it) }
                priceDefault.let { activityProductFormPrice.setText(it) }

                AlertDialog.Builder(context)
                    .setMessage("Altere os dados do produto como desejar")
                    .setTitle("Produto selecionado: $titleDefault")
                    .setView(root)
                    .setPositiveButton("Atualizar") { _, _ ->
                        val title = activityProductFormTitleProduct.text.toString()
                        val description = activityProductFormDescription.text.toString()
                        val price = activityProductFormPrice.text.toString()
                        val newPrice = BigDecimal(price)

                        adapter.updateItem(position, title, description, newPrice)
                    }
                    .setNegativeButton("Deletar") { _, _ ->
                        adapter.delete(position)
                    }
                    .show()
            }

    }
}