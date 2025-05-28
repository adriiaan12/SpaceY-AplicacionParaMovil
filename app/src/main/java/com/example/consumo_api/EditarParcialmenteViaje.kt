package com.example.consumo_api

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.ViajeViewModel

import android.widget.Toast
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.consumo_api.ui.theme.azulboton

@Composable
fun ViajePatchScreen(viewModel: ViajeViewModel) {
    var id by remember { mutableStateOf("") }
    var campo by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }

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

    val antonio = FontFamily(
        Font(R.font.antonio_semibold) // puedes agregar otros estilos como bold, italic, etc.
    )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = id, onValueChange = { id = it },colors=customTextFieldColors, label = { Text("ID del viaje", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = campo, onValueChange = { campo = it },colors=customTextFieldColors, label = { Text("Campo a editar (nombre, correo, etc)", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = valor, onValueChange = { valor = it },colors=customTextFieldColors, label = { Text("Nuevo valor", color=Color.White, fontFamily = antonio) })

        Button(onClick = {
            val convertedValue: Any = when (campo) {
                "edad", "id" -> valor.toIntOrNull() ?: 0
                "enPeligro" -> valor.toBooleanStrictOrNull() ?: false
                else -> valor
            }

            viewModel.patchViaje(id.toInt(), mapOf(campo to convertedValue))
            Toast.makeText(context, "Viaje editado correctamente", Toast.LENGTH_SHORT).show()
        }) {
            Text("Editar Parcialmente", fontFamily = antonio)
        }
    }
}