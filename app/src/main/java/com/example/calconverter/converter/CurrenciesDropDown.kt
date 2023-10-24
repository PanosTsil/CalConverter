package com.example.calconverter.converter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calconverter.ConverterInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrenciesDropdownMenu(isFirstCurrency: Boolean, onInput: (ConverterInput) -> Unit) {
    val currencyList = CurrencyList()
    var isExpanded by remember { mutableStateOf(false) }
    var currency by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        },
        modifier = Modifier.padding(8.dp)
    ) {
        TextField(
            value = currency,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            for (currencies in currencyList.allCurrenciesList()) {
                DropdownMenuItem(
                    text = {
                        Text(text = currencies)
                    },
                    onClick = {
                        if (isFirstCurrency) {
                            onInput(ConverterInput.FirstCurrency(currencies))
                        } else {
                            onInput(ConverterInput.SecondCurrency(currencies))
                        }
                        currency = currencies
                        isExpanded = false
                    }
                )
            }
        }
    }
}