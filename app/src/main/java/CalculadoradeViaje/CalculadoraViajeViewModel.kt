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

    private var _precio = MutableStateFlow("")
    val precio: StateFlow<String> = _precio.asStateFlow()

    private var _costo = MutableStateFlow(0.0)
    val costo: StateFlow<Double> = _costo.asStateFlow()
    private var _pasajeros = MutableStateFlow("")
    val pasajeros: StateFlow<String> = _pasajeros.asStateFlow()

    private var _costoPorPersona = MutableStateFlow(0.0)
    val costoPorPersona: StateFlow<Double> = _costoPorPersona.asStateFlow()
    fun cambiarDistancia(valor:String){
        _distancia.value=valor
        calcular()
    }

    fun cambiarEficiencia(valor: String) {
        _eficiencia.value = valor
        calcular()
    }

    fun cambiarPrecio(valor: String) {
        _precio.value = valor
        calcular()
    }

    fun cambiarPasajeros(valor: String) {
        _pasajeros.value = valor
        calcular()
    }
    private fun calcular() {
        if (_pasajeros.value.isNotEmpty()) {
            val pasajeros = _pasajeros.value.toDouble()
            _costoPorPersona.value = _costo.value
        }
        val d = _distancia.value.toDouble()
        val e = _eficiencia.value.toDouble()
        val p= _precio.value.toDouble()

        _litros.value = d / e
        _costo.value=_litros.value*p
    }

}