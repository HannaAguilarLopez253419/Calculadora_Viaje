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
    fun borrarTodo() {
        _distancia.value = ""
        _eficiencia.value = ""
        _precio.value = ""
        _pasajeros.value = ""
        _litros.value = 0.0
        _costo.value = 0.0
        _costoPorPersona.value = 0.0
    }
    private fun calcular() {
        val d = _distancia.value.toDoubleOrNull()
        val e = _eficiencia.value.toDoubleOrNull()
        val p = _precio.value.toDoubleOrNull()
        val pas = _pasajeros.value.toDoubleOrNull()

        if (d != null && e != null && p != null && pas != null && e > 0 && pas > 0) {
            _litros.value = d / e
            _costo.value = _litros.value * p
            _costoPorPersona.value = _costo.value / pas
        } else {
            _litros.value = 0.0
            _costo.value = 0.0
            _costoPorPersona.value = 0.0
        }
    }

}