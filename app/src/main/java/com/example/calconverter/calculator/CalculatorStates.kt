package com.example.calconverter.calculator

import com.example.calconverter.Operation

data class CalculatorStates(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val firstOperator: Operation? = null,
    val placeholderOperator: Operation? = null
)
