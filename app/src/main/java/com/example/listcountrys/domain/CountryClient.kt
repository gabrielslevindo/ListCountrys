package com.example.listcountrys.domain

import com.example.listcountrys.domain.dataclass.DetailedCountry
import com.example.listcountrys.domain.dataclass.SimpleCountry

interface CountryClient {
    //Interface que declara os metodos para obter os paises;
    //e o pais específico.
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code:String): DetailedCountry?

}