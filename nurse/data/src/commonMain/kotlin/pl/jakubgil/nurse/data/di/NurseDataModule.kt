package pl.jakubgil.nurse.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.jakubgil.nurse.data.NurseDatabaseRepositoryImpl
import pl.jakubgil.nurse.domain.NurseDatabaseRepository

val nurseDataModule = module {
    factoryOf(::NurseDatabaseRepositoryImpl) bind NurseDatabaseRepository::class
}
