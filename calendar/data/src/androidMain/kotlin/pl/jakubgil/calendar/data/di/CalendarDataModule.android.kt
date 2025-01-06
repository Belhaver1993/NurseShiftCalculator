package pl.jakubgil.calendar.data.di

import org.koin.dsl.module
import pl.jakubgil.calendar.data.holidays.HolidayFileReader
import pl.jakubgil.calendar.data.holidays.HolidayFileReaderImpl

internal actual val calendarDataPlatformModule = module {
    factory<HolidayFileReader> { HolidayFileReaderImpl(get(), get()) }
}