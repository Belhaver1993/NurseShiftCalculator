package pl.jakubgil.database.core.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.database.core.DatabaseDriverFactory

internal actual val databaseCorePlatformModule = module {
    singleOf(::DatabaseDriverFactory)
}
