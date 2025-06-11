package com.example.listcountrys.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listcountrys.presentation.screens.CountriesScreen
import com.example.listcountrys.presentation.ui.theme.ListCountrysTheme
import com.example.listcountrys.presentation.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListCountrysTheme {
              val viewModel: CountriesViewModel = hiltViewModel()

                CountriesScreen(
                    viewModel = viewModel,
                    onSelectCountry = viewModel::selectedCountry,
                )
            }
        }
    }
}
