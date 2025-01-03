package pl.jakubgil.calendar.data

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import pl.jakubgil.calendar.domain.CalendarRepository
import pl.jakubgil.calendar.domain.model.Day
import pl.jakubgil.calendar.domain.model.DayType
import pl.jakubgil.calendar.domain.model.Month
import pl.jakubgil.calendar.data.holidays.HolidaysProvider

internal class CalendarRepositoryImpl(
    private val holidaysProvider: HolidaysProvider,
) : CalendarRepository {

    override suspend fun createMonth(year: Int, month: Int): Month {
        val holidays = holidaysProvider.provide()
        var date = LocalDate(year, month, 1)
        val monthLength = date.month.length(date.isLeapYear())

        val days = mutableListOf<Day>()

        for (i in 1..monthLength) {
            val isHoliday = holidays.any { it.year == date.year && it.month == date.monthNumber && it.day == date.dayOfMonth }
            val dayType = when {
                isHoliday && date.dayOfWeek.value == DayOfWeek.SUNDAY.value ->
                    DayType.HOLIDAY_AND_SUNDAY
                isHoliday -> DayType.HOLIDAY
                date.dayOfWeek.value == 6 -> DayType.SATURDAY
                date.dayOfWeek.value == 7 -> DayType.SUNDAY
                else -> DayType.WORKDAY
            }

            days.add(Day(dayOfMonth = date.dayOfMonth, type = dayType))
            date = date.plus(1, DateTimeUnit.DAY)
        }

        return Month(
            year = year,
            month = month,
            days = days,
        )
    }

    private fun LocalDate.isLeapYear(): Boolean =
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

}