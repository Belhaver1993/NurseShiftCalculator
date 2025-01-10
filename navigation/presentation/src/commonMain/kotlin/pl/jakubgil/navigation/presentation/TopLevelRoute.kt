package pl.jakubgil.navigation.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

data class TopLevelRoute<T : Route>(val name: StringResource, val icon: ImageVector, val route: T)
