package com.example.listcountrys.domain.dataclass

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val language: List<String>,
    val continent: String

)

fun DetailedCountry.toCountryData(): CountryData {
    return CountryData(
        code = this.code,
        name = this.name,
        emoji = this.emoji,
        capital = this.capital
    )

}