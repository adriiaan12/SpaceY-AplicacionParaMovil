package com.example.consumo_api

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.ViajeViewModel

@Composable
fun ViajeDetailScreen(viewModel: ViajeViewModel) {
    var id by remember { mutableStateOf("") }
    var buscarTrigger by remember { mutableStateOf(false) } // Trigger manual
    val context = LocalContext.current
    val viaje = viewModel.viajeDetail // Recolectamos directamente el detalle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID del viaje") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            buscarTrigger = true // Disparamos la búsqueda
        }) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Efecto lanzado al pulsar "Buscar"
        LaunchedEffect(buscarTrigger) {
            if (buscarTrigger) {
                id.toIntOrNull()?.let {
                    viewModel.buscarViajePorId(it)
                    // Esperamos brevemente para dejar que viajeDetail se actualice
                    kotlinx.coroutines.delay(500)
                    if (viewModel.viajeDetail != null) {
                        Toast.makeText(context, "Viaje encontrado correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Viaje no encontrado", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(context, "ID inválido", Toast.LENGTH_SHORT).show()
                }
                buscarTrigger = false // Reiniciamos el trigger
            }
        }

        // Mostrar tarjeta si se ha encontrado
        viaje?.let {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Red),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Id: ${it.id}")
                    Text("Nombre: ${it.nombre}")
                    Text("Apellido: ${it.apellido}")
                    Text("Telefono: ${it.tlf}")
                    Text("Correo: ${it.correo}")
                    Text("Fecha Inicio: ${it.fechaini}")
                    Text("Fecha Fin: ${it.fechafin}")
                    Text("Plan: ${it.plan}")
                    Text("Tipo de Cohete: ${it.tipocohete}")
                    Text("Numero de Personas: ${it.npersonas}")
                    Text("Embarazada: ${if (it.embarazada) "Sí" else "No"}")
                }
            }
        }
    }
}

