package com.example.consumo_api


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.consumo_api.modules.Animal
import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import android.widget.Toast
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.consumo_api.ui.theme.azulboton
import com.example.consumo_api.ui.theme.colorboton


@Composable
fun ViajeCreateScreen(viewModel: ViajeViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var fechaini by remember { mutableStateOf("") }
    var fechafin by remember { mutableStateOf("") }
    var tlf by remember { mutableStateOf("") }
    var npersonas by remember { mutableStateOf("") }
    var embarazada by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

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




    Column(modifier = androidx.compose.ui.Modifier.verticalScroll(scrollState)) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it },colors=customTextFieldColors, label = { Text("Nombre", color=Color.White) })
        OutlinedTextField(value = apellido, onValueChange = { apellido = it },colors=customTextFieldColors, label = { Text("Apellido", color=Color.White) })
        OutlinedTextField(value = correo, onValueChange = { correo = it },colors=customTextFieldColors, label = { Text("correo", color=Color.White) })
        OutlinedTextField(value = tlf, onValueChange = { tlf = it },colors=customTextFieldColors, label = { Text("Teléfono", color=Color.White) })
        OutlinedTextField(value = fechaini, onValueChange = { fechaini = it },colors=customTextFieldColors, label = { Text("Fecha ini", color=Color.White) })
        OutlinedTextField(value = fechafin, onValueChange = { fechafin = it },colors=customTextFieldColors, label = { Text("Fecha Fin", color=Color.White) })
        OutlinedTextField(value = npersonas, onValueChange = { npersonas = it },colors=customTextFieldColors, label = { Text("Personas", color=Color.White) })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = embarazada, onCheckedChange = { embarazada = it })
            Text("Embarazada",color = Color.White)
        }

        Button(onClick = {

                viewModel.agregarViaje(Viaje(0,nombre,apellido,correo,tlf,npersonas.toIntOrNull() ?: 0,apellido
                    ,apellido,fechaini,fechafin,embarazada))

            Toast.makeText(context, "Viaje creado correctamente", Toast.LENGTH_SHORT).show()


        },colors = ButtonDefaults.buttonColors(
            containerColor = colorboton,       // Fondo del botón
            contentColor = Color.White,        // Color del texto/icono
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )) {
            Text("Crear Animal")
        }
    }
}

