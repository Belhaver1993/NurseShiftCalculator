package pl.jakubgil.database.core.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.jakubgil.database.core.DatabaseDriverFactory
import pl.jakubgil.database.core.NurseShiftCalculatorDatabase
import pl.jakubgil.nurse.data.NurseShiftCalculatorDatabase as NurseDatabase

val databaseCoreModule: Module get() = module {
    includes(databaseCoreCommonModule, databaseCorePlatformModule)
}

private val databaseCoreCommonModule = module {
    single<NurseDatabase> {
        NurseShiftCalculatorDatabase(
            (get() as DatabaseDriverFactory).createDriver(),
        )
    }
}

internal expect val databaseCorePlatformModule: Module
