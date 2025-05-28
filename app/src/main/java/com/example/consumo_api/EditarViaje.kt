package com.example.consumo_api

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.Animal
import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.consumo_api.ui.theme.azulboton


@Composable
fun ViajeUpdateScreen(viewModel: ViajeViewModel) {
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var fechaini by remember { mutableStateOf("") }
    var fechafin by remember { mutableStateOf("") }
    var tlf by remember { mutableStateOf("") }
    var tipocohete by remember { mutableStateOf("") }
    var plan by remember { mutableStateOf("") }

    var npersonas by remember { mutableStateOf("") }
    var embarazada by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val scrollState = rememberScrollState()

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

    Column(modifier = Modifier.padding(16.dp).verticalScroll(scrollState) ) {
        OutlinedTextField(value = id, onValueChange = { id = it },colors=customTextFieldColors, label = { Text("ID a actualizar", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = nombre, onValueChange = { nombre = it },colors=customTextFieldColors, label = { Text("Nombre", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = apellido, onValueChange = { apellido = it },colors=customTextFieldColors, label = { Text("Apellido", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = correo, onValueChange = { correo = it },colors=customTextFieldColors, label = { Text("correo", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = tlf, onValueChange = { tlf = it },colors=customTextFieldColors, label = { Text("Teléfono", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = fechaini, onValueChange = { fechaini = it },colors=customTextFieldColors, label = { Text("Fecha ini", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = fechafin, onValueChange = { fechafin = it },colors=customTextFieldColors, label = { Text("Fecha Fin", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = npersonas, onValueChange = { npersonas = it },colors=customTextFieldColors, label = { Text("Personas", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = plan, onValueChange = { plan = it },colors=customTextFieldColors, label = { Text("Plan", color=Color.White, fontFamily = antonio) })
        OutlinedTextField(value = tipocohete, onValueChange = { tipocohete = it },colors=customTextFieldColors, label = { Text("Tipo de Cohete", color=Color.White, fontFamily = antonio) })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = embarazada, onCheckedChange = { embarazada = it })
            Text("Embarazada",color = Color.White, fontFamily = antonio)
        }

        Button(onClick = {
            val viaje = Viaje(id.toInt(),nombre,apellido,correo,tlf,npersonas.toIntOrNull() ?: 0,tipocohete,plan,fechaini,fechafin,embarazada)
            viewModel.editarViaje(id.toInt(), viaje)
            Toast.makeText(context, "Viaje editado correctamente", Toast.LENGTH_SHORT).show()
        }) {
            Text("Actualizar Viaje", fontFamily = antonio)
        }
    }
}