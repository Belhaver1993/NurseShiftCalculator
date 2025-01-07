package pl.jakubgil.nurse.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pl.jakubgil.nurse.domain.useCases.CreateNurse
import pl.jakubgil.nurse.domain.useCases.DeleteNurse
import pl.jakubgil.nurse.domain.useCases.GetAllNurses

val nurseDomainModule = module {
    factoryOf(::CreateNurse)
    factoryOf(::DeleteNurse)
    factoryOf(::GetAllNurses)
}
