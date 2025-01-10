package pl.jakubgil.nurseshiftcalculator.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import nurseshiftcalculator.composeapp.generated.resources.Res
import nurseshiftcalculator.composeapp.generated.resources.top_level_route_nurses
import nurseshiftcalculator.composeapp.generated.resources.top_level_route_shifts_create
import nurseshiftcalculator.composeapp.generated.resources.top_level_route_shifts_list
import pl.jakubgil.navigation.presentation.BottomNavigationRoutesProvider
import pl.jakubgil.navigation.presentation.Route
import pl.jakubgil.navigation.presentation.TopLevelRoute
import pl.jakubgil.nurse.presentation.list.NurseListRoute
import pl.jakubgil.nurseshiftcalculator.test.CalculatorCreateRoute
import pl.jakubgil.nurseshiftcalculator.test.CalculatorListRoute

class BottomNavigationRoutesProviderImpl : BottomNavigationRoutesProvider {

    override fun provide(): List<TopLevelRoute<Route>> = listOf(
        TopLevelRoute(Res.string.top_level_route_shifts_create, Icons.Default.Add, CalculatorCreateRoute),
        TopLevelRoute(Res.string.top_level_route_shifts_list, Icons.AutoMirrored.Filled.List, CalculatorListRoute),
        TopLevelRoute(Res.string.top_level_route_nurses, Icons.Default.Person, NurseListRoute),
    )
}
