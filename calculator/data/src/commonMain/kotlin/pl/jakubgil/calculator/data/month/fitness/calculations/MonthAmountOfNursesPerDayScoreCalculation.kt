package pl.jakubgil.calculator.data.month.fitness.calculations

import pl.jakubgil.calculator.data.GeneticGlobalProperties
import pl.jakubgil.calculator.data.month.fitness.MonthlyScheduleFitnessScoreCalculation
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation.Companion.SCORE_FOR_MINIMAL_AMOUNT_OF_SHIFTS_WITH_SHORT_SHIFT
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation.Companion.SCORE_PER_LESSER_AMOUNT_OF_SHIFTS
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation.Companion.SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_SPECIAL_SHIFT
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation.Companion.SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_WORKDAY
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calculator.domain.model.ShiftSchedule
import pl.jakubgil.calculator.domain.model.violation.MonthlyScheduleScoreViolation
import pl.jakubgil.calendar.domain.model.DayType
import pl.jakubgil.calendar.domain.model.Month

internal class MonthAmountOfNursesPerDayScoreCalculation(private val globalProperties: GeneticGlobalProperties) :
    MonthlyScheduleFitnessScoreCalculation {

    override fun calculate(month: Month, monthlySchedule: MonthlySchedule): List<MonthlyScheduleScoreViolation> {
        val violations = mutableListOf<MonthlyScheduleScoreViolation>()

        for (day in 0 until month.numberOfDays()) {
            val shifts = monthlySchedule.nurses.map { it.shifts[day] }

            when (month.days[day].type) {
                DayType.WORKDAY -> calculateWorkdayShiftScore(day + 1, shifts)
                else -> calculateSpecialShiftScore(day + 1, shifts)
            }?.let {
                violations.add(it)
            }

            calculateNightShiftScore(day + 1, shifts)?.let {
                violations.add(it)
            }
        }

        return violations
    }

    private fun calculateWorkdayShiftScore(dayOfMonth: Int, shifts: List<ShiftSchedule>): MonthlyScheduleScoreViolation? {
        val numberOfWorkdayShifts = shifts.count { it.shift == Shift.DAY || it.shift == Shift.SHORT }

        return when {
            numberOfWorkdayShifts !in globalProperties.numberOfNursesPerWorkday -> {
                if (numberOfWorkdayShifts < globalProperties.numberOfNursesPerWorkday.first) {
                    LesserAmountOfShifts(dayOfMonth)
                } else {
                    MoreThanMaxAmountOfShiftsInWorkday(dayOfMonth)
                }
            }
            numberOfWorkdayShifts == globalProperties.numberOfNursesPerWorkday.first && shifts.find { it.shift == Shift.SHORT } != null -> {
                MinimalAmountOfShiftsWithShortShift(dayOfMonth)
            }
            else -> {
                null
            }
        }
    }

    private fun calculateSpecialShiftScore(dayOfMonth: Int, shifts: List<ShiftSchedule>): MonthlyScheduleScoreViolation? {
        // SHORT shifts are not possible in special shifts
        val numberOfWorkdayShifts = shifts.count { it.shift == Shift.DAY }

        return when {
            numberOfWorkdayShifts != globalProperties.numberOfNursesPerSpecialShift -> {
                if (numberOfWorkdayShifts < globalProperties.numberOfNursesPerSpecialShift) {
                    LesserAmountOfShifts(dayOfMonth)
                } else {
                    MoreThanMaxAmountOfShiftsInSpacialShift(dayOfMonth)
                }
            }
            else -> {
                null
            }
        }
    }

    private fun calculateNightShiftScore(dayOfMonth: Int, shifts: List<ShiftSchedule>): MonthlyScheduleScoreViolation? {
        val numberOfWorkdayShifts = shifts.count { it.shift == Shift.NIGHT }

        return when {
            numberOfWorkdayShifts != globalProperties.numberOfNursesPerSpecialShift -> {
                if (numberOfWorkdayShifts < globalProperties.numberOfNursesPerSpecialShift) {
                    LesserAmountOfShifts(dayOfMonth)
                } else {
                    MoreThanMaxAmountOfShiftsInSpacialShift(dayOfMonth)
                }
            }
            else -> {
                null
            }
        }
    }

    companion object {
        const val SCORE_FOR_MINIMAL_AMOUNT_OF_SHIFTS_WITH_SHORT_SHIFT = -9999

        const val SCORE_PER_LESSER_AMOUNT_OF_SHIFTS = -10000

        const val SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_WORKDAY = -100
        const val SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_SPECIAL_SHIFT = -10001
    }
}

data class MinimalAmountOfShiftsWithShortShift(
    val dayOfMonth: Int,
    override val fitnessScore: Int = SCORE_FOR_MINIMAL_AMOUNT_OF_SHIFTS_WITH_SHORT_SHIFT,
) : MonthlyScheduleScoreViolation

data class LesserAmountOfShifts(val dayOfMonth: Int, override val fitnessScore: Int = SCORE_PER_LESSER_AMOUNT_OF_SHIFTS) :
    MonthlyScheduleScoreViolation

data class MoreThanMaxAmountOfShiftsInWorkday(
    val dayOfMonth: Int,
    override val fitnessScore: Int = SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_WORKDAY,
) : MonthlyScheduleScoreViolation

data class MoreThanMaxAmountOfShiftsInSpacialShift(
    val dayOfMonth: Int,
    override val fitnessScore: Int = SCORE_PER_MORE_THAN_MAX_AMOUNT_OF_SHIFTS_IN_SPECIAL_SHIFT,
) : MonthlyScheduleScoreViolation
