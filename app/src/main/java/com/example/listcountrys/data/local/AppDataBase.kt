package com.example.listcountrys.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listcountrys.domain.dataclass.CountryData

@Database(entities = [CountryData::class], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}