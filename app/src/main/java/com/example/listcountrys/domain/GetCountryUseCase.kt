package com.example.listcountrys.domain

import com.example.listcountrys.domain.dataclass.DetailedCountry
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

// class para executar a busca dos paises;
@ViewModelScoped
class GetCountryUseCase @Inject constructor(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient
            .getCountry(code = code)

    }
}