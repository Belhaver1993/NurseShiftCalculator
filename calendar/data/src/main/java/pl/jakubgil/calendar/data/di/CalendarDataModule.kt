package pl.jakubgil.calendar.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.calendar.domain.CalendarRepository
import pl.jakubgil.calendar.data.CalendarRepositoryImpl
import pl.jakubgil.calendar.data.holidays.HolidayFileReader
import pl.jakubgil.calendar.data.holidays.HolidaysProvider

val calendarDataModule = module {
    factory<CalendarRepository> { CalendarRepositoryImpl(get()) }
    singleOf(::HolidaysProvider)
    factoryOf(::HolidayFileReader)
}