package com.example.calconverter.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calconverter.SharedViewModel

@Composable
fun ConverterScreen(navController: NavController, viewModel: SharedViewModel) {
    val state = viewModel.converterState
    val spacing = 8.dp
    ConverterLayout(
        converterStates = state,
        onInput = viewModel::onConverterInput,
        spacing = spacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}