package pl.jakubgil.calculator.data.nurse.fitness.calculations

import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessScoreCalculation
import pl.jakubgil.calculator.data.nurse.possibility.NursePossibleShiftsCreator
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month
import kotlin.math.abs

internal class NurseShiftsShouldMatchWithPossibleShiftsScoreCalculation(private val possibleShiftsCreator: NursePossibleShiftsCreator) :
    NurseFitnessScoreCalculation {

    override fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        val possibleShifts = possibleShiftsCreator.create(month, nurseMonthlySchedule.numberOfVacations)

        val numberOfFullShiftsDifference =
            abs(possibleShifts.numberOfFullShifts - nurseMonthlySchedule.numberOfFullShifts)
        val numberOfShortShiftsDifference =
            abs(possibleShifts.numberOfShortShifts - nurseMonthlySchedule.numberOfShortShifts)
        val numberOfNoShiftsDifference =
            abs(possibleShifts.numberOfEmptyShifts - nurseMonthlySchedule.numberOfEmptyShifts)
        val numberOfVacationsDifference =
            abs(possibleShifts.numberOfVacations - nurseMonthlySchedule.numberOfVacations)

        val sum = numberOfFullShiftsDifference + numberOfShortShiftsDifference +
            numberOfNoShiftsDifference + numberOfVacationsDifference

        return sum * PENALTY_SCORE_FOR_VIOLATION
    }

    private companion object {
        const val PENALTY_SCORE_FOR_VIOLATION = -102
    }
}
