package pl.jakubgil.calendar.data.holidays

import pl.jakubgil.calendar.data.holidays.model.Holiday

interface HolidayFileReader {

    suspend fun read(): List<Holiday>

}
