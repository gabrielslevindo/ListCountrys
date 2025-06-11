package com.example.listcountrys.data.remote

import com.apollographql.apollo.ApolloClient
import com.example.listcountrys.CountriesQuery
import com.example.listcountrys.CountryQuery
import com.example.listcountrys.domain.CountryClient
import com.example.listcountrys.domain.dataclass.DetailedCountry
import com.example.listcountrys.domain.dataclass.SimpleCountry
import javax.inject.Inject

class ApolloCountryClient @Inject constructor(
    private val apolloClient: ApolloClient
):CountryClient {

    // Fun para buscar todos os paises e receber as infos deles;
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    // fun para buscar o pais especificado e os seus detalhes;
    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()

    }
}