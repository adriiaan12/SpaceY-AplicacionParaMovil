package com.example.consumo_api


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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





    Column {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("correo") })
        OutlinedTextField(value = tlf, onValueChange = { tlf = it }, label = { Text("tlf") })
        OutlinedTextField(value = fechaini, onValueChange = { fechaini = it }, label = { Text("Fecha ini") })
        OutlinedTextField(value = fechafin, onValueChange = { fechafin = it }, label = { Text("Fecha Fin") })
        OutlinedTextField(value = npersonas, onValueChange = { npersonas = it }, label = { Text("npersonas") })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = embarazada, onCheckedChange = { embarazada = it })
            Text("En peligro")
        }
        Button(onClick = {

                viewModel.agregarViaje(Viaje(0,nombre,apellido,correo,tlf,npersonas.toInt(),apellido
                    ,apellido,fechaini,fechafin,embarazada))

        }) {
            Text("Crear Animal")
        }
    }
}

