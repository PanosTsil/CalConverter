package com.example.calconverter.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calconverter.SharedViewModel

@Composable
fun CalculatorScreen(navController: NavController, viewModel: SharedViewModel) {
    val state = viewModel.calculatorState
    val spacing = 8.dp
    CalculatorLayout(
        calculatorStates = state,
        onInput = viewModel::onCalculatorInput,
        spacing = spacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}