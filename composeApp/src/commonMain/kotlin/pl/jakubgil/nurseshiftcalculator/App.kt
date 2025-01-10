package pl.jakubgil.nurseshiftcalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pl.jakubgil.calendar.domain.model.Month
import pl.jakubgil.calendar.domain.useCase.GetMonth
import pl.jakubgil.navigation.presentation.view.BottomNavigationBar
import pl.jakubgil.nurse.presentation.create.NurseCreateRoute
import pl.jakubgil.nurse.presentation.create.NurseCreateScreen
import pl.jakubgil.nurse.presentation.list.NurseListRoute
import pl.jakubgil.nurse.presentation.list.NurseListScreen
import pl.jakubgil.nurseshiftcalculator.di.NurseShiftCalculatorKoinApp
import pl.jakubgil.nurseshiftcalculator.test.CalculatorCreate
import pl.jakubgil.nurseshiftcalculator.test.CalculatorCreateRoute
import pl.jakubgil.nurseshiftcalculator.test.CalculatorList
import pl.jakubgil.nurseshiftcalculator.test.CalculatorListRoute

@Composable
@Preview
fun App() {
    NurseShiftCalculatorKoinApp {
        var monthState by remember { mutableStateOf<Month?>(null) }
        val getMonth = koinInject<GetMonth>()
        LaunchedEffect(Unit) {
            val month = getMonth.invoke(2025, 1)

            monthState = month
        }

        MaterialTheme {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Scaffold(
                modifier = Modifier.safeDrawingPadding(),
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        currentDestination = currentDestination,
                    )
                },
                content = { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = CalculatorCreateRoute,
                        modifier = Modifier.padding(paddingValues),
                    ) {
                        composable<CalculatorCreateRoute> {
                            CalculatorCreate()
                        }
                        composable<CalculatorListRoute> {
                            CalculatorList()
                        }
                        composable<NurseListRoute> {
                            NurseListScreen(navController)
                        }
                        composable<NurseCreateRoute> {
                            NurseCreateScreen(navController)
                        }
                    }
                },
            )
        }
    }
}
