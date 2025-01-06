package pl.jakubgil.nurseshiftcalculator.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val appModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}
