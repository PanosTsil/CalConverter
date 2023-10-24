package com.example.calconverter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calconverter.calculator.CalculatorStates
import com.example.calconverter.converter.ConverterStates
import com.example.calconverter.converter.model.RateValues
import com.example.calconverter.converter.networkapi.FixerApi
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.floor
import kotlin.reflect.KProperty1


class SharedViewModel: ViewModel() {
    private var ratesUiState: RateValues? = null
    private var exchangeRate: Double? = 0.0

    var displayToast = MutableLiveData<Int?>()

    var calculatorState by mutableStateOf(CalculatorStates())
        private set

    var converterState by mutableStateOf(ConverterStates())

    fun onCalculatorInput(calculatorInput: CalculatorInput) {
        when(calculatorInput) {
            is CalculatorInput.Clear -> calculatorState = CalculatorStates()
            is CalculatorInput.Backspace -> deleteLastCharacterCalc()
            is CalculatorInput.Digit -> hasDigitCalc(calculatorInput.digit)
            is CalculatorInput.Decimal -> hasDecimalCalc()
            is CalculatorInput.Operations -> hasOperation(calculatorInput.operation)
            is CalculatorInput.Equals -> giveResult()
        }
    }

    fun onConverterInput(converterInput: ConverterInput) {
        when(converterInput) {
            is ConverterInput.Clear -> convClear()
            is ConverterInput.Navigate -> converterState = ConverterStates()
            is ConverterInput.Backspace -> deleteLastCharacterConv()
            is ConverterInput.Digit -> hasDigitConv(converterInput.digit)
            is ConverterInput.Decimal -> hasDecimalConv()
            is ConverterInput.FirstCurrency -> hasCurrency(converterInput.currency1)
            is ConverterInput.SecondCurrency -> hasConversion(converterInput.currency2)
        }
    }

    private fun convClear() {
        converterState = converterState.copy(currencyToConvert = "", convertedCurrency = "")
    }

    private fun hasConversion(secondCurrency: String) {
        if (converterState.firstCurrency.isBlank()) {
            displayToast.value = FIRST_CURRENCY_EMPTY
            return
        }
        converterState = converterState.copy(secondCurrency = secondCurrency)
        if (exchangeRate != null && ratesUiState != null) {
            exchangeRate = readInstanceProperty(ratesUiState!!, converterState.secondCurrency)
        }
    }

    private fun hasCurrency(firstCurrency: String) {
        converterState = converterState.copy(firstCurrency = firstCurrency)
        viewModelScope.launch {
            try {
                val rates = FixerApi.retrofitService.getExchangeRates(converterState.firstCurrency)
                ratesUiState = rates.rates
            } catch (e: IOException) {
                Log.d(LOG_TAG, "Something went wrong in the fetching of rates $e")
            }
        }
    }

    private fun hasOperation(operation: Operation) {
        if (calculatorState.firstNumber.isNotBlank()){
            if (calculatorState.firstOperator == null){
                calculatorState =calculatorState.copy(firstOperator = operation)
            } else {
                calculatorState = calculatorState.copy(placeholderOperator = operation)
                giveResult()
                calculatorState = calculatorState.copy(firstOperator = calculatorState.placeholderOperator, secondNumber = "")
            }
        }
    }

    private fun giveResult() {
        val firstNumber = calculatorState.firstNumber.toDoubleOrNull()
        val secondNumber = calculatorState.secondNumber.toDoubleOrNull()
        var isInt = false
        if (firstNumber != null && secondNumber != null) {
            val result = when (calculatorState.firstOperator) {
                is Operation.Addition -> firstNumber + secondNumber
                is Operation.Subtraction -> firstNumber - secondNumber
                is Operation.Multiplication -> firstNumber * secondNumber
                is Operation.Percentage -> (firstNumber * secondNumber) / 100
                is Operation.Division -> {
                    if (firstNumber == 0.toDouble() && secondNumber == 0.toDouble()) {
                        displayToast.value = UNDEFINED
                        return
                    } else if (secondNumber == 0.toDouble()) {
                        displayToast.value = DIV_ZERO
                        return
                    } else {
                        firstNumber / secondNumber
                    }
                }
                null -> return
            }
            if (result == floor(result)) {
                isInt = true
            }
            calculatorState = if (isInt) {
                calculatorState.copy(firstNumber = result.toInt().toString().take(12), secondNumber = "", firstOperator = null)
            } else {
                calculatorState.copy(firstNumber = result.toString().take(12), secondNumber = "", firstOperator = null)
            }
        }
    }

    private fun hasDecimalCalc() {
        if (calculatorState.firstOperator == null && !calculatorState.firstNumber.contains(".")) {
            calculatorState = if (calculatorState.firstNumber.isNotBlank()) {
                calculatorState.copy(firstNumber = calculatorState.firstNumber + ".")
            } else {
                calculatorState.copy(firstNumber = calculatorState.firstNumber + "0.")
            }
            return
        }
        if (calculatorState.firstOperator != null && !calculatorState.secondNumber.contains(".")) {
            calculatorState = if (calculatorState.secondNumber.isNotBlank()) {
                calculatorState.copy(secondNumber = calculatorState.secondNumber + ".")
            } else {
                calculatorState.copy(secondNumber = calculatorState.secondNumber + "0.")
            }
            return
        }
    }

    private fun hasDecimalConv() {
        if (converterState.firstCurrency.isBlank() || converterState.secondCurrency.isBlank()) {
            displayToast.value = CURRENCIES_EMPTY
            return
        }
        if (converterState.currencyToConvert.length >= MAX_LENGTH) {
            displayToast.value = NUMBER_TOO_LONG
            return
        }
        if (!converterState.currencyToConvert.contains(".")) {
            converterState = if (converterState.currencyToConvert.isNotBlank()) {
                converterState.copy(currencyToConvert = converterState.currencyToConvert + ".")
            } else {
                converterState.copy(currencyToConvert = converterState.currencyToConvert + "0.")
            }
            return
        }
    }

    private fun hasDigitCalc(digit: Int) {
        calculatorState = if (calculatorState.firstOperator == null){
            if (calculatorState.firstNumber.length >= MAX_LENGTH){
                displayToast.value = NUMBER_TOO_LONG
                return
            }
            calculatorState.copy(firstNumber = calculatorState.firstNumber + digit)
        } else {
            if (calculatorState.secondNumber.length >= MAX_LENGTH){
                displayToast.value = NUMBER_TOO_LONG
                return
            }
            calculatorState.copy(secondNumber = calculatorState.secondNumber + digit)
        }
    }

    private fun hasDigitConv(digit: Int) {
        if (converterState.firstCurrency.isBlank() || converterState.secondCurrency.isBlank()) {
            displayToast.value = CURRENCIES_EMPTY
            return
        }
        if (converterState.currencyToConvert.length >= MAX_LENGTH) {
            displayToast.value = NUMBER_TOO_LONG
            return
        }
        converterState = converterState.copy(
            currencyToConvert = converterState.currencyToConvert + digit,
        )
        converterState = converterState.copy(
            convertedCurrency = (converterState.currencyToConvert.toDouble() * (exchangeRate ?: 0.0)).toString()
        )
    }

    private fun deleteLastCharacterCalc() {
        when {
            calculatorState.secondNumber.isNotBlank() -> calculatorState = calculatorState.copy(secondNumber = calculatorState.secondNumber.dropLast(1))
            calculatorState.firstOperator != null -> calculatorState = calculatorState.copy(firstOperator = null)
            calculatorState.firstNumber.isNotBlank() -> calculatorState = calculatorState.copy(firstNumber = calculatorState.firstNumber.dropLast(1))
        }
    }

    private fun deleteLastCharacterConv() {
        when {
            converterState.currencyToConvert.isNotBlank() -> converterState = converterState.copy(currencyToConvert = converterState.currencyToConvert.dropLast(1))
        }
        converterState = if (converterState.currencyToConvert.isNotBlank()) {
            converterState.copy(
                convertedCurrency = (converterState.currencyToConvert.toDouble() * (exchangeRate ?: 0.0)).toString()
            )
        } else {
            converterState.copy(
                convertedCurrency = ""
            )
        }
    }

    private fun <R> readInstanceProperty(instance: Any, propertyName: String): R {
        val property = instance::class.members
            .first { it.name == propertyName } as KProperty1<Any, *>
        return property.get(instance) as R
    }

    companion object {
        private const val LOG_TAG = "[SharedViewModel]"
        private const val MAX_LENGTH = 6
        private const val UNDEFINED = 1
        private const val DIV_ZERO = 2
        private const val NUMBER_TOO_LONG = 3
        private const val CURRENCIES_EMPTY = 4
        private const val FIRST_CURRENCY_EMPTY = 5
    }
}
