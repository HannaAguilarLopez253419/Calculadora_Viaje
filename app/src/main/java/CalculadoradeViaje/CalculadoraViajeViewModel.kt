package CalculadoradeViaje

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculadoraViajeViewModel: ViewModel() {
    private var _distancia= MutableStateFlow("")
    val distancia: StateFlow<String> = _distancia.asStateFlow()

    private var _eficiencia= MutableStateFlow("")
    val eficiencia: StateFlow<String> = _eficiencia.asStateFlow()

    private var _litros= MutableStateFlow(0.0)
    val litros: StateFlow<Double> = _litros.asStateFlow()
    fun cambiarDistancia(valor:String){
        _distancia.value=valor
        calcular()
    }

    fun cambiarEficiencia(valor: String) {
        _eficiencia.value = valor
        calcular()
    }

    private fun calcular() {
        val d = _distancia.value.toDouble()
        val e = _eficiencia.value.toDouble()

        _litros.value = d / e
    }
}