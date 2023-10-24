package com.example.calconverter

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calconverter.calculator.CalculatorScreen
import com.example.calconverter.converter.ConverterScreen

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = ComposeScreens.CalculatorScreen.name
    ) {

        composable(ComposeScreens.CalculatorScreen.name) {
            CalculatorScreen(navController = navController, viewModel = sharedViewModel)
        }

        composable(ComposeScreens.ConverterScreen.name) {
            ConverterScreen(navController = navController, viewModel = sharedViewModel)
        }
    }
}