package com.ebc.appdescuento.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ebc.appdescuento.model.CalculadoraState

//Heredamos la funcionalidad de View Model
class CalculadoraViewModel: ViewModel() {

    //Estos datos son los que vamos a observar
    var state by mutableStateOf(CalculadoraState())
        private set

    fun onValue(value: String, text: String) {
        when(text) {
            "precio" -> state = state.copy(precio = value)
            "descuento" -> state = state.copy(descuento = value)
        }
    }

    fun calcular() {
        val precio = state.precio
        val descuento = state.descuento

        state = if (precio != "" && descuento != "") {
            state.copy(
                precioDescuento = calcularPrecioDescuento(precio.toDouble(), descuento.toDouble()),
                totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            )
        } else {
            state.copy(
                mostrarAlerta = true
            )
        }
    }

    fun limpiar() {
        state = state.copy(
            precio = "",
            descuento = "",
            precioDescuento = 0.0,
            totalDescuento = 0.0
        )
    }

    fun cancelarAlerta() {
        state = state.copy(
            mostrarAlerta = false
        )
    }

    private fun calcularPrecioDescuento(precio: Double, descuento:Double): Double {
        val descuentoFinal = calcularDescuento(precio, descuento)
        return precio - descuentoFinal
    }

    private fun calcularDescuento(precio: Double, descuento:Double): Double {
        val descuento = precio * (descuento/100)

        return descuento
    }
}