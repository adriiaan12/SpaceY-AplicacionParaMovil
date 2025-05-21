package com.example.consumo_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel

@Composable
fun ListaViajes(viewModel: ViajeViewModel = ViajeViewModel()) {
    val viajes by viewModel.viajes.collectAsState()
    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.obtenerViajes()
    }
    LazyColumn {
        items(viajes) { viaje ->
            Card( colors = CardDefaults.cardColors(containerColor = Color.Red,
            ),

                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Id: ${viaje.id}")
                    Text("Nombre: ${viaje.nombre}")
                    Text("Especie: ${viaje.apellido}")
                    Text("Edad: ${viaje.tlf}")
                    Text("Hábitat: ${viaje.correo}")
                    Text("En peligro: ${if (viaje.embarazada) "Sí" else "No"}")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        generarPdf(contexto, viajes)
    }) {
        Text("Exportar a PDF")
    }
}


// La forma más simple de mostrar los elementos cargados de la API
@Composable
fun ListaDeViajes(viewModel: ViajeViewModel = ViajeViewModel()) {
    val viajes by viewModel.viajes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerViajes()
    }

    LazyColumn {
        items(viajes) { viaje ->
            Text("${viaje.id}: ${viaje.nombre} (${viaje.apellido})")
        }
    }
}
