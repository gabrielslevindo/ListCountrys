package com.example.listcountrys.presentation.state

import com.example.listcountrys.domain.dataclass.DetailedCountry
import com.example.listcountrys.domain.dataclass.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectCountry: DetailedCountry? = null,
    val isError: String? = null
)
