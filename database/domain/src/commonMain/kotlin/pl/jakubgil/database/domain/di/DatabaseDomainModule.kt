package pl.jakubgil.database.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pl.jakubgil.database.domain.useCases.CreateNurse
import pl.jakubgil.database.domain.useCases.DeleteNurse
import pl.jakubgil.database.domain.useCases.GetAllNurses

val databaseDomainModule = module {
    factoryOf(::CreateNurse)
    factoryOf(::DeleteNurse)
    factoryOf(::GetAllNurses)
}
