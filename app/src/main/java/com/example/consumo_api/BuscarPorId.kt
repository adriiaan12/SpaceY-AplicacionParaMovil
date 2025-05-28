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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.ViajeViewModel
import com.example.consumo_api.ui.theme.azulboton
import com.example.consumo_api.ui.theme.colorboton

@Composable
fun ViajeDetailScreen(viewModel: ViajeViewModel) {
    var id by remember { mutableStateOf("") }
    var buscarTrigger by remember { mutableStateOf(false) } // Trigger manual
    val context = LocalContext.current
    val viaje = viewModel.viajeDetail // Recolectamos directamente el detalle

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White,
        cursorColor = Color.White,
        focusedContainerColor = azulboton,
        unfocusedContainerColor = azulboton,
        disabledContainerColor = azulboton,


        )

    val antonio = FontFamily(
        Font(R.font.antonio_semibold) // puedes agregar otros estilos como bold, italic, etc.
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID del viaje", color=Color.White, fontFamily = antonio) },
            colors = customTextFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            buscarTrigger = true // Disparamos la búsqueda
        },colors = ButtonDefaults.buttonColors(
            containerColor = colorboton,       // Fondo del botón
            contentColor = Color.White,        // Color del texto/icono
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )) {
            Text("Buscar", fontFamily = antonio)
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
                colors = CardDefaults.cardColors(containerColor = azulboton),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Id: ${it.id}",color=Color.White, fontFamily = antonio)
                    Text("Nombre: ${it.nombre}",color=Color.White, fontFamily = antonio)
                    Text("Apellido: ${it.apellido}",color=Color.White, fontFamily = antonio)
                    Text("Telefono: ${it.tlf}",color=Color.White, fontFamily = antonio)
                    Text("Correo: ${it.correo}",color=Color.White, fontFamily = antonio)
                    Text("Fecha Inicio: ${it.fechaini}",color=Color.White, fontFamily = antonio)
                    Text("Fecha Fin: ${it.fechafin}",color=Color.White, fontFamily = antonio)
                    Text("Plan: ${it.plan}",color=Color.White, fontFamily = antonio)
                    Text("Tipo de Cohete: ${it.tipocohete}",color=Color.White, fontFamily = antonio)
                    Text("Numero de Personas: ${it.npersonas}",color=Color.White, fontFamily = antonio)
                    Text("Embarazada: ${if (it.embarazada) "Sí" else "No"}",color=Color.White, fontFamily = antonio)
                }
            }
        }
    }
}

