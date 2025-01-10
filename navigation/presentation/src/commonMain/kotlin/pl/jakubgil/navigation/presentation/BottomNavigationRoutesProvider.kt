package pl.jakubgil.navigation.presentation

interface BottomNavigationRoutesProvider {

    fun provide(): List<TopLevelRoute<*>>
}
