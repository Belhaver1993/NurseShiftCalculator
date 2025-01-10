package pl.jakubgil.nurseshiftcalculator.di

import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.navigation.presentation.BottomNavigationRoutesProvider
import pl.jakubgil.nurseshiftcalculator.navigation.BottomNavigationRoutesProviderImpl

internal val appModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    singleOf<BottomNavigationRoutesProvider>(::BottomNavigationRoutesProviderImpl)
}
