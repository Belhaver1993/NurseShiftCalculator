package pl.jakubgil.calendar.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.calendar.data.CalendarRepositoryImpl
import pl.jakubgil.calendar.data.holidays.HolidaysProvider
import pl.jakubgil.calendar.domain.CalendarRepository

private val calendarDataCommonModule = module {
    factory<CalendarRepository> { CalendarRepositoryImpl(get()) }
    singleOf(::HolidaysProvider)
}

internal expect val calendarDataPlatformModule: Module

val calendarDataModule: Module get() = module {
    includes(calendarDataCommonModule + calendarDataPlatformModule)
}
