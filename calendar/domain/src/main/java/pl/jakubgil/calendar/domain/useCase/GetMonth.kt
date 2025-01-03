package pl.jakubgil.calendar.domain.useCase

import pl.jakubgil.calendar.domain.CalendarRepository
import pl.jakubgil.calendar.domain.model.Month

class GetMonth(
    private val calendarRepository: CalendarRepository,
) {

    suspend fun invoke(year: Int, month: Int): Month = calendarRepository.createMonth(year, month)

}