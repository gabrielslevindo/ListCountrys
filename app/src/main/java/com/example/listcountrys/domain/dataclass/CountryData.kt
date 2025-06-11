package com.example.listcountrys.domain.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryData(
    @PrimaryKey val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)
