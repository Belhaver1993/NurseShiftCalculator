package pl.jakubgil.database.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.database.data.DatabaseProvider
import pl.jakubgil.database.data.NurseDatabaseRepositoryImpl
import pl.jakubgil.database.domain.NurseDatabaseRepository

val databaseDataModule: Module get() = module {
    includes(databaseDataCommonModule, databaseDataPlatformModule)
}

private val databaseDataCommonModule = module {
    singleOf(::DatabaseProvider)
    factory<NurseDatabaseRepository> { NurseDatabaseRepositoryImpl(get()) }
}

expect val databaseDataPlatformModule: Module
