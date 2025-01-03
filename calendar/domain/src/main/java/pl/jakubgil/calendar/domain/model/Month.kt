package pl.jakubgil.calendar.domain.model

data class Month(
    val year: Int,
    val month: Int,
    val days: List<Day>
) {

    fun numberOfDays(): Int = days.size

    fun numberOfWorkingDays() = days.count { it.type == DayType.WORKDAY }
}
