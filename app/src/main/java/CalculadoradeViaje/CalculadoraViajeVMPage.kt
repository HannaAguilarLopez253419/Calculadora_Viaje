package CalculadoradeViaje

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import java.nio.file.WatchEvent
import androidx.compose.material3.FilterChip
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChipDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraViajeVmPage(
    viewModel: CalculadoraViajeViewModel = viewModel()
) {
    val distancia by viewModel.distancia.collectAsStateWithLifecycle()
    val eficiencia by viewModel.eficiencia.collectAsStateWithLifecycle()
    val precio by viewModel.precio.collectAsStateWithLifecycle()
    val pasajeros by viewModel.pasajeros.collectAsStateWithLifecycle()
    val carga by viewModel.carga.collectAsStateWithLifecycle()
    val calculado by viewModel.calculado.collectAsStateWithLifecycle()
    val litrosBase by viewModel.litrosBase.collectAsStateWithLifecycle()
    val litrosExtra by viewModel.litrosExtra.collectAsStateWithLifecycle()
    val litros by viewModel.litros.collectAsStateWithLifecycle()
    val costo by viewModel.costo.collectAsStateWithLifecycle()
    val costoPorPersona by viewModel.costoPorPersona.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Calculadora de viajes")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            if (calculado) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(520.dp)
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top =20.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 40.dp,
                                    topEnd=40.dp,
                                    bottomStart = 25.dp,
                                    bottomEnd = 25.dp
                                )
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(
                                top=80.dp,
                                start=25.dp,
                                end=25.dp,
                                bottom=25.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text="Gasto total",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "%.2f L".format(costo),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )
                        Text(
                            text = "Consumo estimado"
                        )
                        Text(
                            text = "%.2f L".format(litros),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )
                        Text(
                            text = "Consumo base: %.2f L".format(litrosBase)
                        )
                        Text(
                            text = "Extra por carga: %.2f L".format(litrosExtra)
                        )
                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )
                        Text(
                            text = "Costo por persona"
                        )
                        Text(
                            text = "$%.2f".format(costoPorPersona),
                            style = MaterialTheme.typography.titleLarge

                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Button(
                            onClick = {
                                viewModel.nuevoViaje()
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Reiniciar"
                            )
                        }
                    }
                }
            } else {

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text("Ingresa la distancia")
                        OutlinedTextField(
                            value = distancia,
                            onValueChange = { distanciaingresada ->
                                viewModel.cambiarDistancia(distanciaingresada)
                            },
                            label = {
                                Text("Distancia en km")
                            },
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text("Ingresa el consumo del vehiculo")
                        OutlinedTextField(
                            value = eficiencia,
                            onValueChange = { eficienciaingresada ->
                                viewModel.cambiarEficiencia(eficienciaingresada)
                            },
                            label = {
                                Text("Eficiencia en km/L")
                            },
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text("Ingresa el precio de gasolina")
                        OutlinedTextField(
                            value = precio,
                            onValueChange = { precioingresado ->
                                viewModel.cambiarPrecio(precioingresado)
                            },
                            label = {
                                Text("Gasolina en L")
                            },
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text("Ingresa la cantidad de personas que viajaran")
                        OutlinedTextField(
                            value = pasajeros,
                            onValueChange = { pasajerosingresados ->
                                viewModel.cambiarPasajeros(pasajerosingresados)
                            },
                            label = {
                                Text("Num pasajeros")
                            },
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text("Nivel de carga:")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilterChip(
                                selected = carga == 1.0,
                                onClick = {
                                    viewModel.cambiarCarga(1.0)
                                },
                                label = {
                                    Text("Ligero")
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFA8B58A)
                                )
                            )
                            FilterChip(
                                selected = carga == 1.10,
                                onClick = {
                                    viewModel.cambiarCarga(1.10)
                                },
                                label = {
                                    Text("Media")
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFA8B58A)
                                )
                            )
                            FilterChip(
                                selected = carga == 1.20,
                                onClick = {
                                    viewModel.cambiarCarga(1.20)
                                },
                                label = {
                                    Text("Alto")
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFA8B58A)
                                )
                            )
                        }
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Button(
                            onClick = {
                                viewModel.calcularViaje()
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB46A72)
                            )
                        ) {
                            Text("Calcular")
                        }
                    }
                }
            }
        }
    }
}