package pl.jakubgil.calendar.domain.model

data class Day(val dayOfMonth: Int, val type: DayType)

enum class DayType {
    WORKDAY, SATURDAY, SUNDAY, HOLIDAY, HOLIDAY_AND_SUNDAY
}
