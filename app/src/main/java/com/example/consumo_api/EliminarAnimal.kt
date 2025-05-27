package com.example.consumo_api

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import com.example.consumo_api.modules.ViajeViewModel
import com.example.consumo_api.modules.RetrofitClient
import com.example.consumo_api.ui.theme.azulboton
import com.example.consumo_api.ui.theme.colorboton


@Composable
fun ViajeDeleteScreen(viewModel: ViajeViewModel) {
    var id by remember { mutableStateOf("") }
    val context = LocalContext.current

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White,
        cursorColor = Color.White,
        focusedContainerColor = azulboton,
        unfocusedContainerColor = azulboton,
        disabledContainerColor = azulboton,
        )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it },colors= customTextFieldColors, label = { Text("ID del animal a eliminar", color=Color.White) })

        Button(onClick = {
            viewModel.eliminarViaje(id.toInt())
            Toast.makeText(context, "Viaje eliminado correctamente", Toast.LENGTH_SHORT).show()
        },colors = ButtonDefaults.buttonColors(
            containerColor = colorboton,       // Fondo del botón
            contentColor = Color.White,        // Color del texto/icono
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )) {
            Text("Eliminar Animal")
        }
    }
}