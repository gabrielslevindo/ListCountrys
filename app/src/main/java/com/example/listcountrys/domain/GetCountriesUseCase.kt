package com.example.listcountrys.domain

import com.example.listcountrys.domain.dataclass.SimpleCountry
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

// class para executar a busca da classificação dos paises;
@ViewModelScoped
class GetCountriesUseCase @Inject constructor(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}