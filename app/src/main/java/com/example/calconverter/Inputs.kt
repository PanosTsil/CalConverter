package com.example.calconverter

sealed class CalculatorInput {
    object Clear: CalculatorInput()
    object Backspace: CalculatorInput()
    object Decimal: CalculatorInput()
    object Equals: CalculatorInput()
    data class Digit(val digit: Int): CalculatorInput()
    data class Operations(val operation: Operation): CalculatorInput()
}

sealed class Operation (val symbol: String) {
    object Addition: Operation("+")
    object Subtraction: Operation("-")
    object Multiplication: Operation("x")
    object Division: Operation("/")
    object Percentage: Operation("%")
}

sealed class ConverterInput {
    object Clear: ConverterInput()
    object Backspace: ConverterInput()
    object Decimal: ConverterInput()
    object Navigate: ConverterInput()
    data class FirstCurrency(val currency1: String): ConverterInput()
    data class SecondCurrency(val currency2: String): ConverterInput()
    data class Digit(val digit: Int): ConverterInput()
}
