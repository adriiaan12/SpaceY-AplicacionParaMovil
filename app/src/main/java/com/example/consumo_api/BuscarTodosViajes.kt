package com.example.consumo_api.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.consumo_api.R
import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel
import com.example.consumo_api.generarPdf
import com.example.consumo_api.ui.theme.azulboton
import com.example.consumo_api.ui.theme.colorboton

@Composable
fun ListaViajes(viewModel: ViajeViewModel = ViajeViewModel()) {
    val viajes by viewModel.viajes.collectAsState()
    val contexto = LocalContext.current
    val antonio = FontFamily(
        Font(R.font.antonio_semibold) // puedes agregar otros estilos como bold, italic, etc.
    )

    LaunchedEffect(Unit) {
        viewModel.obtenerViajes()
    }

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(viajes) { viaje ->
            Card(
                colors = CardDefaults.cardColors(containerColor = azulboton),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Id: ${viaje.id}",color=Color.White, fontFamily = antonio)
                    Text("Nombre: ${viaje.nombre}",color=Color.White, fontFamily = antonio)
                    Text("Apellido: ${viaje.apellido}",color=Color.White, fontFamily = antonio)
                    Text("Telefono: ${viaje.tlf}",color=Color.White, fontFamily = antonio)
                    Text("Correo: ${viaje.correo}",color=Color.White, fontFamily = antonio)
                    Text("Fecha Inicio: ${viaje.fechaini}",color=Color.White, fontFamily = antonio)
                    Text("Fecha Fin: ${viaje.fechafin}",color=Color.White, fontFamily = antonio)
                    Text("Plan: ${viaje.plan}",color=Color.White, fontFamily = antonio)
                    Text("Tipo de Cohete: ${viaje.tipocohete}",color=Color.White, fontFamily = antonio)
                    Text("Numero de Personas: ${viaje.npersonas}",color=Color.White, fontFamily = antonio)
                    Text("Embarazada: ${if (viaje.embarazada) "Sí" else "No"}",color=Color.White, fontFamily = antonio)
                }
            }
        }

        // Este es el botón como parte del scroll
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { generarPdf(contexto, viajes) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                ,colors = ButtonDefaults.buttonColors(
                    containerColor = colorboton,       // Fondo del botón
                    contentColor = Color.White,        // Color del texto/icono
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text("Exportar a PDF", fontFamily = antonio)
            }
        }
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
