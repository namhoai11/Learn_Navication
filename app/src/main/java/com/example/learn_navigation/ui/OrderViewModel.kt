package com.example.learn_navigation.ui


import androidx.lifecycle.ViewModel
import com.example.learn_navigation.data.taxRate
import com.example.learn_navigation.model.MenuItem
import com.example.learn_navigation.model.MenuItem.EntreeItem
import com.example.learn_navigation.model.MenuItem.AccompanimentItem
import com.example.learn_navigation.model.MenuItem.SideDishItem
import com.example.learn_navigation.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        val previousItem = _uiState.value.entree
        updateItem(entree, previousItem)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousItem = _uiState.value.sideDish
        updateItem(sideDish, previousItem)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousItem = _uiState.value.accompaniment
        updateItem(accompaniment, previousItem)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    init {
        resetOrder()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                entree = if (newItem is EntreeItem) newItem else currentState.entree,
                sideDish = if (newItem is SideDishItem) newItem else currentState.sideDish,
                accompaniment = if (newItem is AccompanimentItem) newItem else currentState.accompaniment,
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax
            )
        }
    }
}

fun Double.formatPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    format.maximumFractionDigits = 2 // Giữ lại 2 chữ số thập phân
    return format.format(this)
}