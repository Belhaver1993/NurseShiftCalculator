package pl.jakubgil.calendar.data.holidays

import pl.jakubgil.calendar.data.holidays.model.Holiday

internal class HolidaysProvider(
    private val holidayFileReader: HolidayFileReader,
) {
    private val cachedHolidays: List<Holiday>? = null

    suspend fun provide(): List<Holiday> {
        if (!cachedHolidays.isNullOrEmpty()) {
            return cachedHolidays
        }

        return holidayFileReader.read()
    }

    private companion object {
        private const val TAG = "HolidaysProvider"
    }

}