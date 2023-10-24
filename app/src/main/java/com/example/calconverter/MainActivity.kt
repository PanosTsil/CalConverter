package com.example.calconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calconverter.ui.theme.CalConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalConverterTheme {
                ComposeNavigation()
                val viewModel = viewModel<SharedViewModel>()
                val displayToast = viewModel.displayToast.observeAsState()
                displayToast.let { liveValue ->
                    if (liveValue.value != null) {
                        when (liveValue.value) {
                            1 -> Toast.makeText(
                                applicationContext,
                                "Undefined",
                                Toast.LENGTH_SHORT
                            ).show()
                            2 -> Toast.makeText(
                                applicationContext,
                                "Impossible to divide with 0",
                                Toast.LENGTH_SHORT
                            ).show()
                            3 -> Toast.makeText(
                                applicationContext,
                                "Number is too long",
                                Toast.LENGTH_SHORT
                            ).show()
                            4 -> Toast.makeText(
                                applicationContext,
                                "Please, select currencies for conversion",
                                Toast.LENGTH_SHORT
                            ).show()
                            5 -> Toast.makeText(
                                applicationContext,
                                "Please, select the original currency first",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                    viewModel.displayToast.value = null
                }
            }
        }
    }
}