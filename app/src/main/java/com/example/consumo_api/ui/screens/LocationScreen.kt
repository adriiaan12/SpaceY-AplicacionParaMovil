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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.consumo_api.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLocalizacion() {
    val scrollState = rememberScrollState()
    val estacion= LatLng(5.167713, -52.683994)

    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(estacion, 16f)
    }

    val Orbitron = FontFamily(
        Font(R.font.orbitron_regular) // puedes agregar otros estilos como bold, italic, etc.
    )

    val antonio = FontFamily(
        Font(R.font.antonio_semibold) // puedes agregar otros estilos como bold, italic, etc.
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Localización -  Lanzamiento",
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
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = Orbitron
                )
                Spacer(Modifier.height(8.dp))
                Text("El Centro Espacial de Guayana (CSG) es el puerto espacial europeo situado en Kourou, en la Guayana Francesa, " +
                        "un territorio de ultramar de Francia en América del Sur. Inaugurado en 1968, es operado por el Centro Nacional de " +
                        "Estudios Espaciales (CNES) en colaboración con la Agencia Espacial Europea (ESA) y Arianespace.", fontFamily = antonio)

            }
        }



        Text(
            text = "Mapa interactivo",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontFamily = Orbitron
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
                    state = MarkerState(position = estacion),
                    title = "Lanzamiento",

                )
            }
        }
    }
}