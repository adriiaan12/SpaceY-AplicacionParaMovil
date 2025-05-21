package com.example.consumo_api.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.consumo_api.modules.ViajeViewModel
import com.example.consumo_api.ViajeDetailScreen
import com.example.consumo_api.ListaViajes
import com.example.consumo_api.ViajeCreateScreen
import com.example.consumo_api.ViajeUpdateScreen
import com.example.consumo_api.ViajePatchScreen
import com.example.consumo_api.ViajeDeleteScreen
import com.example.consumo_api.modules.Viaje


@Composable
fun AnimalTabLayout(viewModel: ViajeViewModel = ViajeViewModel()) {
    val tabs = listOf("Listar", "Buscar", "Crear", "Actualizar", "Parcial", "Eliminar")
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTab) {
            0 -> ListaViajes(viewModel)   //GET All
            1 -> ViajeDetailScreen(viewModel) // GET Id
            2 -> ViajeCreateScreen(viewModel) //POST
            3 -> ViajeUpdateScreen(viewModel)  //PUT
            4 -> ViajePatchScreen(viewModel)  //PATCH
            5 -> ViajeDeleteScreen(viewModel)  //DELETE
        }
        Text(viewModel.message, modifier = Modifier.padding(8.dp), color = Color.Green)
    }
}
