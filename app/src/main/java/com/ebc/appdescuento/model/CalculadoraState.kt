package com.ebc.appdescuento.model

data class CalculadoraState(
    val precio: String = "",
    val descuento: String = "",
    val precioDescuento: Double = 0.0,
    val totalDescuento: Double = 0.0,
    val mostrarAlerta: Boolean = false
)