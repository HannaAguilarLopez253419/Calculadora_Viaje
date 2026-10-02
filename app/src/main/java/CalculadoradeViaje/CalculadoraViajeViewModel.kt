package CalculadoradeViaje

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculadoraViajeViewModel: ViewModel() {
    private var _distancia= MutableStateFlow("")
    val distancia: StateFlow<String> = _distancia.asStateFlow()

    fun cambiarDistancia(valor:String){
        _distancia.value=valor
    }
}