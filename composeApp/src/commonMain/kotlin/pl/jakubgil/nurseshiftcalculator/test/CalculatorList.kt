package pl.jakubgil.nurseshiftcalculator.test

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import pl.jakubgil.navigation.presentation.Route

@Composable
fun CalculatorList(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text("Calculator list")
    }
}

@Serializable
object CalculatorListRoute : Route
