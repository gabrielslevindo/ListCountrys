package com.example.listcountrys.domain.repository

import com.example.listcountrys.data.local.AppDataBase
import com.example.listcountrys.domain.dataclass.CountryData
import timber.log.Timber
import javax.inject.Inject

private const val tag = "CountryRepository"

class CountryRepository @Inject constructor(
     val appDataBase: AppDataBase
) {
    suspend fun insertCountry(countryData: CountryData) = try {
        appDataBase.countryDao().insertCountry(countryData = countryData)
    } catch (e: Exception) {
        Timber.tag(tag = tag).e(e)
        0
    }

    suspend fun getAllCountries() = try {
        appDataBase.countryDao().getAllCountries() ?: emptyList()
    } catch (e: Exception) {
        Timber.tag(tag = tag).e(e)
        emptyList()
    }

    suspend fun deleteCountryByCode(code: String) = try {
        appDataBase.countryDao().deleteCountryByCode(code)
    } catch (e: Exception) {
        Timber.tag(tag).e(e)
    }

    suspend fun isFavorite(code: String): Boolean = try {
        appDataBase.countryDao().getCountryByCode(code) != null
    } catch (e: Exception) {
        Timber.tag(tag).e(e)
        false
    }
}