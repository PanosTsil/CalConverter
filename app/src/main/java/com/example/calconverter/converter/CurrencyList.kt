package com.example.calconverter.converter

class CurrencyList() {

    fun allCurrenciesList(): ArrayList<String> {
        val allCurrencies = arrayListOf<String>()
        allCurrencies.add("EUR")
        allCurrencies.add("USD")
        allCurrencies.add("HUF")
        allCurrencies.add("GBP")
        allCurrencies.add("AUD")
        allCurrencies.add("JPY")

        return allCurrencies
    }
}