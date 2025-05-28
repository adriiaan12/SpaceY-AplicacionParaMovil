package com.example.consumo_api.ui.screens

// TaskIntroScreen.kt


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumo_api.R
import com.example.consumo_api.modules.ViajeViewModel
import com.example.consumo_api.ui.screens.MainScreen
import com.example.consumo_api.ui.theme.colorboton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskIntroScreen(navController: NavController) {

    val antonio = FontFamily(
        Font(R.font.antonio_semibold) // puedes agregar otros estilos como bold, italic, etc.
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { navController.navigate("tasks") },colors = ButtonDefaults.buttonColors(
            containerColor = colorboton,       // Fondo del botón
            contentColor = Color.White,        // Color del texto/icono
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )) {
            Text("Ir a Formulario de Reserva", fontFamily = antonio)
        }
    }
}


