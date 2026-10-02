package CalculadoradeViaje

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculadoraViajeVmPage(
    viewModel: CalculadoraViajeViewModel = viewModel()
) {

    val distancia by viewModel.distancia.collectAsStateWithLifecycle()
    val eficiencia by viewModel.eficiencia.collectAsStateWithLifecycle()
    val litros by viewModel.litros.collectAsStateWithLifecycle()
    val precio by viewModel.precio.collectAsStateWithLifecycle()
    val costo by viewModel.costo.collectAsStateWithLifecycle()
    val pasajeros by viewModel.pasajeros.collectAsStateWithLifecycle()
    val costoPorPersona by viewModel.costoPorPersona.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculadora de Viaje",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = distancia,
            onValueChange = {
                viewModel.cambiarDistancia(it)
            },
            label = {
                Text("Distancia en km")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = eficiencia,
            onValueChange = {
                viewModel.cambiarEficiencia(it)
            },
            label = {
                Text("Eficiencia (km/L)")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Litros necesarios: $litros",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = {
                viewModel.cambiarPrecio(it)
            },
            label = {
                Text("Precio por litro ($)")
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Costo total: $costo",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pasajeros,
            onValueChange = {
                viewModel.cambiarPasajeros(it)
            },
            label = {
                Text("Número de pasajeros")
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Costo por persona: $costoPorPersona",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
