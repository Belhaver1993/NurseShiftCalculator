package pl.jakubgil.calculator.data.nurse.possibility

import pl.jakubgil.calculator.data.GeneticGlobalProperties
import pl.jakubgil.calendar.domain.model.Month
import kotlin.time.Duration.Companion.minutes
import kotlin.time.times

internal class NursePossibleShiftsCreator(private val globalProperties: GeneticGlobalProperties) {

    fun create(month: Month, numberOfVacations: Int): NursePossibleShifts {
        val workingHoursInMonth = (month.numberOfWorkingDays() - numberOfVacations) * globalProperties.hoursPerDay
        val numberOfFullShifts = (workingHoursInMonth / globalProperties.hoursPerFullShift).toInt()

        val remainingHours = workingHoursInMonth - numberOfFullShifts * globalProperties.hoursPerFullShift
        val numberOfShortShifts: Int = if (remainingHours > 0.minutes) 1 else 0

        val numberOfEmptyShifts: Int = month.numberOfDays() - numberOfFullShifts - numberOfShortShifts - numberOfVacations

        return NursePossibleShifts(
            numberOfFullShifts = numberOfFullShifts,
            numberOfShortShifts = numberOfShortShifts,
            numberOfEmptyShifts = numberOfEmptyShifts,
            numberOfVacations = numberOfVacations,
        )
    }
}

internal data class NursePossibleShifts(
    val numberOfFullShifts: Int,
    val numberOfShortShifts: Int,
    val numberOfEmptyShifts: Int,
    val numberOfVacations: Int,
) {

    val total: Int = numberOfFullShifts + numberOfShortShifts + numberOfVacations + numberOfEmptyShifts
}
