package com.example.learn_navigation.model


import java.text.NumberFormat
import java.util.Locale

sealed class MenuItem(
    open val name: String,
    open val description: String,
    open val price: Double
) {
    data class EntreeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class SideDishItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class AccompanimentItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)


    fun getFormattedPrice(): String {
        // Sử dụng Locale.US để cố định định dạng tiền tệ thành USD
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 2 // Giữ lại 2 chữ số thập phân
        return format.format(price)
    }
}