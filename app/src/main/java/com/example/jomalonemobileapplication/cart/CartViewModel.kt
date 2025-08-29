package com.example.jomalonemobileapplication.cart

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    fun addItemToCart(item: ShoppingCartItem) {
        val currentItems = _uiState.value.items.toMutableList()
        val existingItem = currentItems.find { it.id == item.id }
        if (existingItem != null) {
            // If the item already exists in the cart, update its quantity
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + item.quantity)
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = updatedItem
        } else {
            currentItems.add(item)
        }

        _uiState.value = _uiState.value.copy(items = currentItems)
    }

    fun removeItemFromCart(item: ShoppingCartItem) {
        val currentItems = _uiState.value.items.toMutableList()
        val existingItem = currentItems.find { it.id == item.id }
        if (existingItem != null) {
            currentItems.remove(existingItem)
        }
    }
}