package com.example.listcountrys.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listcountrys.domain.dataclass.SimpleCountry
import com.example.listcountrys.presentation.viewmodel.CountriesViewModel

@Composable
fun CountriesScreen(
    viewModel: CountriesViewModel,
    onSelectCountry: (code: String) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchCountry.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(top = 30.dp)
    ) {
        SearchTextField(
            query = searchQuery,
            onQueryChanged = viewModel::onSearch
        )

        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.countries) { country ->
                        CountryItem(
                            country = country,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectCountry(country.code) }
                                .padding(16.dp)
                        )
                    }
                }
            }
                state.selectCountry?.let { selected ->
                    CountryDialog(
                        country = state.selectCountry,
                        onDismiss = {
                            viewModel.dismissCoutryDialog()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp),
                        isFavorite = isFavorite,
                        onFavoriteChange = { viewModel.setFavorite(country = selected) }
                    )
                }
            }
        }
    }

@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Search Country") }
    )
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = country.emoji,
                fontSize = 36.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Capital: ${country.capital}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryItemPreview() {

    val simpleCountry = SimpleCountry(
        code = "1",
        name = "Brasil",
        emoji = "\uD83C\uDDE7\uD83C\uDDF7",
        capital = "Brasilia"
    )

    CountryItem(country = simpleCountry)
}

@Preview(showBackground = true)
@Composable
fun CountriesScreenPreview() {

    CountriesScreen(
        viewModel = viewModel(),
        onSelectCountry = {},
    )
}
