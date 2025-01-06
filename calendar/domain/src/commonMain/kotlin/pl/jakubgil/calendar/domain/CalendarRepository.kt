package pl.jakubgil.calendar.domain

import pl.jakubgil.calendar.domain.model.Month

interface CalendarRepository {

    suspend fun createMonth(year: Int, month: Int): Month

}