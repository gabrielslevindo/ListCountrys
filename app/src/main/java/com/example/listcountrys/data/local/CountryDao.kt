package com.example.listcountrys.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.listcountrys.domain.dataclass.CountryData

@Dao
interface CountryDao {
    @Insert
    suspend fun insertCountry(countryData: CountryData)

    @Query("SELECT * FROM CountryData")
    suspend fun getAllCountries(): List<CountryData>

    @Query("DELETE FROM CountryData WHERE code = :code")
    suspend fun deleteCountryByCode(code: String)

    @Query("SELECT * FROM CountryData WHERE code = :code LIMIT 1")
    suspend fun getCountryByCode(code: String): CountryData?
}