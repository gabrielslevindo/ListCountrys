package com.example.listcountrys.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listcountrys.R
import com.example.listcountrys.domain.dataclass.DetailedCountry

@Composable
fun CountryDialog(
    modifier: Modifier = Modifier,
    country: DetailedCountry?,
    onDismiss: () -> Unit,
    isFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier
                .padding(20.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = country?.emoji ?: "",
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = country?.name ?: "",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { onFavoriteChange(!isFavorite) }) {
                        Image(
                            painter = painterResource(if (isFavorite) R.drawable.star_rate  else R.drawable.star_outline),
                            contentDescription = if (isFavorite) "Favorite" else "Favorite"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                val joinedLanguages = remember(country?.language) {
                    country?.language?.joinToString(", ")
                }

                InfoRow(label = "Capital", value = country?.capital)
                InfoRow(label = "Continent", value = country?.continent)
                InfoRow(label = "Currency", value = country?.currency)
                InfoRow(label = "Language(s)", value = joinedLanguages)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
