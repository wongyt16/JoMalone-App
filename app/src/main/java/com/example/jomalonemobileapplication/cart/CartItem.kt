package com.example.jomalonemobileapplication.cart

import com.example.jomalonemobileapplication.R

data class CartItem(
    val id: String,              // Unique ID for the product
    val name: String,            // "Raspberry Ripple Cologne"
    val size: String,            // "100 ml"
    val imageRes: Int,        // Link to product image
    val unitPrice: Double,       // 700.00
    val quantity: Int,           // 1
    val isSelected: Boolean      // Whether the checkbox is ticked
){
    val totalPrice: Double
        get() = unitPrice * quantity
}

object SampleData {
    val cartItems = listOf(
        CartItem(
            id = "1",
            name = "Raspberry Ripple Cologne",
            size = "100 ml",
            imageRes = R.drawable.raspberry,
            unitPrice = 700.00,
            quantity = 1,
            isSelected = false
        ),
        CartItem(
            id = "2",
            name = "Orange Marmalade Cologne",
            size = "100 ml",
            imageRes = R.drawable.orange,
            unitPrice = 670.00,
            quantity = 2,
            isSelected = false

        )
    )
}
