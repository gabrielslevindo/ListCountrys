package com.example.listcountrys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listcountrys.domain.GetCountriesUseCase
import com.example.listcountrys.domain.GetCountryUseCase
import com.example.listcountrys.domain.dataclass.DetailedCountry
import com.example.listcountrys.domain.dataclass.SimpleCountry
import com.example.listcountrys.domain.dataclass.toCountryData
import com.example.listcountrys.domain.repository.CountryRepository
import com.example.listcountrys.presentation.state.CountriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
    private val countryRepository: CountryRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    private val _searchCountry = MutableStateFlow("")
    val searchCountry = _searchCountry.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private var countryList = listOf<SimpleCountry>()

    fun loadData() = viewModelScope.launch(coroutineContext) {
        _state.update { it.copy(isLoading = true) }
        try {
            val countries = getCountriesUseCase.execute()
            countryList = countries

            _state.update {
                it.copy(
                    countries = countries,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isError = "Error"
                )
            }
        }
    }

    fun onSearch(query: String) = viewModelScope.launch(coroutineContext) {
        _searchCountry.value = query
        _state.update { currentState ->
            currentState.copy(
                countries = countryList.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            )
        }
    }

    fun selectedCountry(code: String) = viewModelScope.launch(coroutineContext) {

        val country = getCountryUseCase.execute(code = code)
        val isFav = countryRepository.isFavorite(code)
        _state.update {
            it.copy(
                selectCountry = country
            )
        }
        _isFavorite.value = isFav
    }

    fun dismissCoutryDialog() {
        _state.update {
            it.copy(
                selectCountry = null
            )
        }
        _isFavorite.value = false
    }

    fun setFavorite(country: DetailedCountry) = viewModelScope.launch(coroutineContext) {
        val isCurrentlyFavorite = countryRepository.isFavorite(country.code)
        if (isCurrentlyFavorite) {
            countryRepository.deleteCountryByCode(country.code)
        } else {
            countryRepository.insertCountry(country.toCountryData())
        }
        _isFavorite.value = !isCurrentlyFavorite
    }
}