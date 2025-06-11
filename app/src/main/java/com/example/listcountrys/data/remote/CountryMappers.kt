package com.example.listcountrys.data.remote

import com.example.listcountrys.CountriesQuery
import com.example.listcountrys.CountryQuery
import com.example.listcountrys.domain.dataclass.DetailedCountry
import com.example.listcountrys.domain.dataclass.SimpleCountry

// fun para converter a query em DetailedCountry
fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return  DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        language = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

// fun para converter a query em SimpleCountry
fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return  SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
    )
}