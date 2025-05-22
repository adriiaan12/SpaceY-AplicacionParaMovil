package com.example.consumo_api.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLocalizacion() {
    val scrollState = rememberScrollState()
    val plazaFragela = LatLng(36.5275, -6.2886)

    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(plazaFragela, 16f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Localización - Plaza Fragela 1",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
                Text("Plaza Fragela está en el centro histórico de Cádiz, cerca del Gran Teatro Falla y la playa La Caleta.")
                Text("Zona peatonal con ambiente cultural, ideal para pasear.")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Medios de transporte disponibles",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
                Text("TextoTextoTextoTextoTextoTexto")
                Text("TextoTextoTextoTextoTextoTextoTexto")
                Text("TextoTextoTextoTextoTextoTextoTextoTexto")
                Text("TextoTextoTextoTextoTextoTextoTextoTextoTexto")
            }
        }

        Text(
            text = "Mapa interactivo",
            style = MaterialTheme.typography.titleLarge
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.LightGray)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = plazaFragela),
                    title = "Plaza Fragela 1",
                    snippet = "Centro histórico de Cádiz"
                )
            }
        }
    }
}