package com.example.calconverter.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calconverter.Buttons
import com.example.calconverter.CalculatorInput
import com.example.calconverter.ComposeScreens
import com.example.calconverter.Operation
import com.example.calconverter.ui.theme.DeletionRed
import com.example.calconverter.ui.theme.MediumGray
import com.example.calconverter.ui.theme.OperatorGreen

@Composable
fun CalculatorLayout(
    modifier: Modifier = Modifier,
    calculatorStates: CalculatorStates,
    spacing: Dp = 8.dp,
    navController: NavController,
    onInput: (CalculatorInput) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                text = calculatorStates.firstNumber + (calculatorStates.firstOperator?.symbol ?: "") + calculatorStates.secondNumber,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Light,
                style = TextStyle(fontSize = 80.sp),
                color = Color.White,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "$",
                    textColor = OperatorGreen,
                    onClick = {
                        navController.navigate(ComposeScreens.ConverterScreen.name)
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "C",
                    textColor = DeletionRed,
                    onClick = {
                        onInput(CalculatorInput.Clear)
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "Del",
                    textColor = DeletionRed,
                    onClick = {
                        onInput(CalculatorInput.Backspace)
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "/",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(CalculatorInput.Operations(Operation.Division))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "7",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(7))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "8",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(8))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "9",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(9))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "x",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(CalculatorInput.Operations(Operation.Multiplication))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "4",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(4))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "5",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(5))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "6",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(6))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "-",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(CalculatorInput.Operations(Operation.Subtraction))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "1",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(1))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "2",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(2))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "3",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(3))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "+",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(CalculatorInput.Operations(Operation.Addition))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = ".",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Decimal)
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "0",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Digit(0))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "%",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(CalculatorInput.Operations(Operation.Percentage))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(OperatorGreen)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "=",
                    textColor = Color.White,
                    onClick = {
                        onInput(CalculatorInput.Equals)
                    }
                )
            }
        }
    }
}