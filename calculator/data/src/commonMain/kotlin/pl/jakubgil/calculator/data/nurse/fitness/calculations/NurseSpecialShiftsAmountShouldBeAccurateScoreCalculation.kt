package pl.jakubgil.calculator.data.nurse.fitness.calculations

import pl.jakubgil.calculator.data.GeneticGlobalProperties
import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessScoreCalculation
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calendar.domain.model.DayType
import pl.jakubgil.calendar.domain.model.Month
import kotlin.math.ceil

internal class NurseSpecialShiftsAmountShouldBeAccurateScoreCalculation(private val globalProperties: GeneticGlobalProperties) :
    NurseFitnessScoreCalculation {

    override fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        var score = 0

        val numberOfNightShifts = nurseMonthlySchedule.numberOfNightShifts
        val numberOfVacationDays = nurseMonthlySchedule.numberOfVacations
        val vacationsPercent = if (numberOfVacationDays == 0) {
            1.0
        } else {
            (numberOfVacationDays.toDouble() / month.numberOfDays())
        }

        val numberOfSpecialShiftsWithoutVacations =
            (globalProperties.numberOfSpecialShiftsWithoutVacations.first + globalProperties.numberOfSpecialShiftsWithoutVacations.last) / 2

        val numberOfAverageSpecialShifts = ceil(numberOfSpecialShiftsWithoutVacations * vacationsPercent).toInt()
        val numberOfSpecialShifts = calculateSpecialShiftsInSchedule(month, nurseMonthlySchedule)

        if (numberOfSpecialShifts !in numberOfAverageSpecialShifts - 1..numberOfAverageSpecialShifts + 1) {
            score += SCORE_FOR_AMOUNT_VIOLATION
        }

        val numberOfSpecialShiftsWithoutNights = numberOfSpecialShifts - numberOfNightShifts
        if (numberOfNightShifts <= numberOfSpecialShiftsWithoutNights) {
            score += (numberOfSpecialShiftsWithoutNights - numberOfNightShifts) * SCORE_FOR_NIGHTS_VIOLATION
        }

        var isWeekendShiftPresent = false

        for (i in 0 until month.numberOfDays()) {
            if ((month.days[i].type == DayType.SATURDAY || month.days[i].type == DayType.SUNDAY) &&
                nurseMonthlySchedule.shifts[i].shift == Shift.DAY
            ) {
                isWeekendShiftPresent = true
                break
            }
        }

        if (!isWeekendShiftPresent) {
            score += SCORE_FOR_NO_WEEKEND_SHIFTS_VIOLATION
        }

        return score
    }

    private fun calculateSpecialShiftsInSchedule(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        var numberOfSpecialShifts = 0

        for (i in 0 until month.numberOfDays()) {
            if (month.days[i].type != DayType.WORKDAY && nurseMonthlySchedule.shifts[i].shift == Shift.DAY) {
                numberOfSpecialShifts++
            }
        }

        numberOfSpecialShifts += nurseMonthlySchedule.shifts.filter { it.shift == Shift.NIGHT }.size

        return numberOfSpecialShifts
    }

    private companion object {
        const val SCORE_FOR_AMOUNT_VIOLATION = -99
        const val SCORE_FOR_NIGHTS_VIOLATION = -98
        const val SCORE_FOR_NO_WEEKEND_SHIFTS_VIOLATION = -97
    }
}
