package com.example.consumo_api.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable



import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.example.consumo_api.models.ViewModel_class

import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel

import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import android.content.Context


import androidx.compose.ui.viewinterop.AndroidView

import androidx.compose.material3.TextFieldDefaults
import com.example.consumo_api.ui.theme.azulboton
import com.example.consumo_api.ui.theme.colorboton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: ViewModel_class) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val opcionesRol = listOf("Falcon-9", "Miura-1", "Ariane 5")
    val opcionesRol2 = listOf("Marte", "Jupiter", "Saturno")
    var embarazada by remember { mutableStateOf(false) }

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            viewModel.onFechainiChange("%02d/%02d/%04d".format(d, m + 1, y))
        }, year, month, day)
    }
    val datePickerDialog2 = remember {
        DatePickerDialog(context, { _, y, m, d ->
            viewModel.onFechafinChange("%02d/%02d/%04d".format(d, m + 1, y))
        }, year, month, day)
    }

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White,
        cursorColor = Color.White,
        focusedContainerColor = azulboton,
        unfocusedContainerColor = azulboton,
        disabledContainerColor = azulboton,


    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TextField(
            value = uiState.nombre,
            onValueChange = viewModel::onNombreChange,
            label = { Text("Nombre",color=Color.White)  },
            colors=customTextFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.apellido,
            onValueChange = viewModel::onApellidoChange,
            label = { Text("Apellido",color=Color.White) },
            colors=customTextFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.correo,
            onValueChange = viewModel::onCorreoChange,
            label = { Text("Correo",color=Color.White) },
            colors=customTextFieldColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()

        )

        TextField(
            value = uiState.tlf,
            onValueChange = viewModel::ontlfChange,
            label = { Text("Telefono",color=Color.White) },
            colors=customTextFieldColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )


        TextField(
            value = uiState.npersonas.toString(), // Convertir Int a String
            onValueChange = {
                val numero = it.toIntOrNull() ?: 0 // Convertir String a Int con control de errores
                viewModel.onnpersonasChange(numero)
            },
            label = { Text("Número de Personas",color=Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors=customTextFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.fechaini,
            onValueChange = {},
            enabled = false, // evita edición manual
            label = { Text("Fecha de Inicio Viaje",color=Color.White) },
            colors=customTextFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    datePickerDialog.show()
                }
        )
        TextField(
            value = uiState.fechafin,
            onValueChange = {},
            enabled = false, // evita edición manual
            label = { Text("Fecha de Fin Viaje",color=Color.White) },
            colors=customTextFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    datePickerDialog2.show()
                }
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                readOnly = true,
                value = uiState.tipocohete,
                onValueChange = {},
                label = { Text("Tipo de Cohete",color=Color.White) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = customTextFieldColors,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesRol.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            viewModel.onRolChange(opcion)
                            expanded = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded2,
            onExpandedChange = { expanded2 = !expanded2 }
        ) {
            TextField(
                readOnly = true,
                value = uiState.plan,
                onValueChange = {},
                label = { Text("Tipo de Plan",color=Color.White) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2)
                },
                colors = customTextFieldColors,

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                opcionesRol2.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            viewModel.onPlanChange(opcion)
                            expanded2 = false
                        }
                    )
                }
            }
        }









        uiState.errorMensaje?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = embarazada, onCheckedChange = { embarazada = it })
            Text("Embarazada",color=Color.White)

        }

        Button(
            onClick = {
                viewModel.agregarViaje(Viaje(0,uiState.nombre,uiState.apellido,uiState.correo,uiState.tlf,uiState.npersonas,uiState.tipocohete
                    ,uiState.plan,uiState.fechaini,uiState.fechafin,embarazada))
            },
            modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = colorboton,       // Fondo del botón
            contentColor = Color.White,        // Color del texto/icono
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )
        ) {
            Text("Enviar")
        }



        // Enviar email si validación fue exitosa
        if (uiState.envioExitoso) {
            LaunchedEffect(Unit) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("diazcanoignacio@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Formulario de datos")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        """
                        Nombre: ${uiState.nombre}
                        Apellido 1: ${uiState.apellido}
                        Apellido 2: ${uiState.apellido}
                        DNI: ${uiState.tlf}
                        Email: ${uiState.correo}
                        """.trimIndent()
                    )
                }
                context.startActivity(Intent.createChooser(intent, "Enviar email con..."))
                // Reseteamos estado para evitar múltiples lanzamientos
                //viewModel.onEmailChange("") // ejemplo para reset
            }
        }
    }
}


