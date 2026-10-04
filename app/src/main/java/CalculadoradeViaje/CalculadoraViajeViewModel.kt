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

    private var _calculado = MutableStateFlow(false)
    val calculado: StateFlow<Boolean> = _calculado.asStateFlow()

    private var _carga = MutableStateFlow(1.0)
    val carga : StateFlow<Double> = _carga.asStateFlow()

    private var _litrosBase = MutableStateFlow(0.0)
    val litrosBase: StateFlow<Double> = _litrosBase.asStateFlow()

    private var _litrosExtra = MutableStateFlow(0.0)
    val litrosExtra: StateFlow<Double> = _litrosExtra.asStateFlow()

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

        _litrosBase.value = 0.0
        _litrosExtra.value = 0.0
        _litros.value = 0.0
        _costo.value = 0.0
        _costoPorPersona.value = 0.0

        _carga.value = 1.0
    }
    fun cambiarCarga(valor: Double){
        _carga.value= valor
    }
    fun calcularViaje() {
        calcular()
        _calculado.value = true
    }
    fun nuevoViaje() {
        borrarTodo()
        _calculado.value = false
    }
    private fun calcular() {
        val distancia = _distancia.value.toDoubleOrNull()
        val eficiencia = _eficiencia.value.toDoubleOrNull()
        val precio = _precio.value.toDoubleOrNull()
        val pasajeros = _pasajeros.value.toDoubleOrNull()

        if (
            distancia != null &&
            eficiencia != null &&
            precio != null &&
            pasajeros != null &&
            eficiencia > 0 &&
            pasajeros > 0
        ) {

            _litrosBase.value =
                distancia / eficiencia

            _litrosExtra.value = _litrosBase.value * (_carga.value - 1)

            _litros.value =_litrosBase.value + _litrosExtra.value

            _costo.value = _litros.value * precio

            _costoPorPersona.value =
                _costo.value / pasajeros

        } else {
            _litrosBase.value = 0.0
            _litrosExtra.value = 0.0
            _litros.value = 0.0
            _costo.value = 0.0
            _costoPorPersona.value = 0.0
        }
    }

}