package com.example.calconverter

enum class ComposeScreens {
    CalculatorScreen,
    ConverterScreen;

    companion object {
        fun fromRoute(route: String?): ComposeScreens = when (route?.substringBefore("/")) {
            CalculatorScreen.name -> CalculatorScreen
            ConverterScreen.name -> ConverterScreen
            null -> CalculatorScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}