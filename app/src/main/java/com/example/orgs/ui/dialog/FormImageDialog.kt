package com.example.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.ActivityProductFormImageBinding
import com.example.orgs.extensions.tryLoadImage

class FormImageDialog(private val context: Context) {
    fun showDialog(
        urlDefault: String? = null,
        whenUploadImage: (image: String) -> Unit
    ) {
        ActivityProductFormImageBinding
            .inflate(LayoutInflater.from(context)).apply {
                urlDefault.let {
                    formImageView.tryLoadImage(it)
                    urlTextImage.setText(it)
                }
                uploadImageView.setOnClickListener {
                    val url = urlTextImage.text.toString()
                    formImageView.tryLoadImage(url)

                }
                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Carregar") { _, _ ->
                        val url = urlTextImage.text.toString()
                        Log.i("FormImageDialog", "show: $url")
                        whenUploadImage(url)
                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()
            } // reutilizar o código mantendo uma única dependência
    }
}