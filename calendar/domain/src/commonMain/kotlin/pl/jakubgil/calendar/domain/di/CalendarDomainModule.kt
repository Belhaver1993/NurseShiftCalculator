package pl.jakubgil.calendar.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pl.jakubgil.calendar.domain.useCase.GetMonth

val calendarDomainModule = module {
    factoryOf(::GetMonth)
}
