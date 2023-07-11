package com.example.orgs.model

import java.math.BigDecimal

data class Products(
    var title_product: String,
    var description: String,
    var price: BigDecimal,
    val image: String? = null
    )


