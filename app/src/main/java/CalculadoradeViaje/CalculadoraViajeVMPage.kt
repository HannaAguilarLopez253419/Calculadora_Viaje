package CalculadoradeViaje

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight

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
        containerColor = Color(0xFFF7F1EE),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Calculadora de viajes")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (calculado) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFB46A72))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Gasto total",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                        Text(
                            text = "$%.2f".format(costo),
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "%.2f L de gasolina".format(litros),
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                            .background(Color(0xFFFFFBFE))
                            .padding(24.dp)
                    ) {
                        FilaDesglose(
                            titulo = "Consumo base",
                            valor = "%.2f L".format(litrosBase)
                        )
                        FilaDesglose(
                            titulo = "Extra por carga",
                            valor = "%.2f L".format(litrosExtra)
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        FilaDesglose(
                            titulo = "Costo por persona",
                            valor = "$%.2f".format(costoPorPersona)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { viewModel.nuevoViaje() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB46A72)
                            )
                        ) {
                            Text("Reiniciar")
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            CampoTexto(
                                titulo = "Ingresa la distancia",
                                etiqueta = "Distancia en km",
                                valor = distancia,
                                onValueChange = { viewModel.cambiarDistancia(it) }
                            )
                            CampoTexto(
                                titulo = "Ingresa el consumo del vehiculo",
                                etiqueta = "Eficiencia en km/L",
                                valor = eficiencia,
                                onValueChange = { viewModel.cambiarEficiencia(it) }
                            )
                            CampoTexto(
                                titulo = "Ingresa el precio de gasolina",
                                etiqueta = "Gasolina en L",
                                valor = precio,
                                onValueChange = { viewModel.cambiarPrecio(it) }
                            )
                            CampoTexto(
                                titulo = "Ingresa la cantidad de personas que viajaran",
                                etiqueta = "Num pasajeros",
                                valor = pasajeros,
                                onValueChange = { viewModel.cambiarPasajeros(it) }
                            )
                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )
                            Text("Nivel de carga:")
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                ChipCarga("Ligero", carga == 1.0) { viewModel.cambiarCarga(1.0) }
                                ChipCarga("Media", carga == 1.10) { viewModel.cambiarCarga(1.10) }
                                ChipCarga("Alto", carga == 1.20) { viewModel.cambiarCarga(1.20) }
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
}

@Composable
fun CampoTexto(
    titulo: String,
    etiqueta: String,
    valor: String,
    onValueChange: (String) -> Unit
) {
    Text(titulo)
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(etiqueta) },
        modifier = Modifier.padding(start = 10.dp)
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun ChipCarga(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = seleccionado,
        onClick = onClick,
        label = { Text(texto) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color(0xFFB46A72).copy(alpha = 0.12f),
            labelColor = Color(0xFF5A2F35),
            selectedContainerColor = Color(0xFFB46A72),
            selectedLabelColor = Color.White
        )
    )
}

@Composable
fun FilaDesglose(
    titulo: String,
    valor: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titulo,
            modifier = Modifier.weight(1f),
            color = Color(0xFF49454F)
        )
        Text(
            text = valor,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1C1B1F)
        )
    }
}