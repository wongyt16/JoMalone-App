package com.example.jomalonemobileapplication.cart

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val error: String? = null
) {
    val total = items.sumOf { it.quantity * it.unitPrice }
    val isEmpty = items.isEmpty()
}

data class ShoppingCartItem(
    val id: String,
    val name: String,
    val size: String,
    val imageRes: Int,
    val unitPrice: Double,
    val quantity: Int,
    val isSelected: Boolean
) {
}
