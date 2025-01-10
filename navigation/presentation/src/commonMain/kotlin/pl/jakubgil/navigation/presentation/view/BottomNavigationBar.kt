package pl.jakubgil.navigation.presentation.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.jakubgil.navigation.presentation.BottomNavigationRoutesProvider

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentDestination: NavDestination?,
) {
    val routes = koinInject<BottomNavigationRoutesProvider>().provide()

    BottomNavigation {
        routes.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        topLevelRoute.icon,
                        contentDescription = stringResource(topLevelRoute.name),
                    )
                },
                label = { Text(stringResource(topLevelRoute.name)) },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(
                        topLevelRoute.route::class,
                    )
                } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
