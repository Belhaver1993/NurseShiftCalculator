package pl.jakubgil.database.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.database.data.DatabaseDriverFactory

actual val databaseDataPlatformModule = module {
    singleOf(::DatabaseDriverFactory)
}
