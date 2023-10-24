package com.example.calconverter.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import com.example.calconverter.ConverterInput
import com.example.calconverter.ui.theme.DeletionRed
import com.example.calconverter.ui.theme.MediumGray
import com.example.calconverter.ui.theme.OperatorGreen

@Composable
fun ConverterLayout(
    modifier: Modifier = Modifier,
    converterStates: ConverterStates,
    spacing: Dp = 8.dp,
    navController: NavController,
    onInput: (ConverterInput) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box {
                        CurrenciesDropdownMenu(isFirstCurrency = true, onInput)
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        text = converterStates.currencyToConvert,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Light,
                        style = TextStyle(fontSize = 80.sp),
                        color = Color.White,
                        maxLines = 2
                    )
                }
            }
            Divider(
                color = MediumGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box {
                        CurrenciesDropdownMenu(isFirstCurrency = false, onInput)
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        text = converterStates.convertedCurrency,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Light,
                        style = TextStyle(fontSize = 80.sp),
                        color = Color.White,
                        maxLines = 2
                    )
                }
            }
            Divider(
                color = MediumGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing),
            ) {
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "+",
                    textColor = OperatorGreen,
                    onClick = {
                        onInput(ConverterInput.Navigate)
                        navController.popBackStack()
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "C",
                    textColor = DeletionRed,
                    onClick = {
                        onInput(ConverterInput.Clear)
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "Del",
                    textColor = DeletionRed,
                    onClick = {
                        onInput(ConverterInput.Backspace)
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
                        .weight(1f),
                    symbol = "7",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(7))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "8",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(8))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "9",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(9))
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
                        .weight(1f),
                    symbol = "4",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(4))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "5",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(5))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "6",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(6))
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
                        .weight(1f),
                    symbol = "1",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(1))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "2",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(2))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = "3",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(3))
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
                        .weight(2f),
                    symbol = "0",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Digit(0))
                    }
                )
                Buttons(
                    modifier = Modifier
                        .background(MediumGray)
                        .weight(1f),
                    symbol = ".",
                    textColor = Color.White,
                    onClick = {
                        onInput(ConverterInput.Decimal)
                    }
                )
            }
        }
    }
}