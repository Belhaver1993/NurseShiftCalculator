package pl.jakubgil.calculator.data.nurse.fitness.calculations

import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessScoreCalculation
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calendar.domain.model.Month

internal class NurseAmountOfConsecutiveShiftsScoreCalculation : NurseFitnessScoreCalculation {

    override fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        var score = STARTING_SCORE

        for (i in 0..<nurseMonthlySchedule.shifts.size - 2) {
            val shifts = nurseMonthlySchedule.shifts.subList(i, i + 3)

            if (shifts.filter { it.shift == Shift.DAY || it.shift == Shift.NIGHT || it.shift == Shift.SHORT }.size == 3) {
                score += SCORE_PER_TOO_MUCH_CONSECUTIVE_SHIFT_REMOVED
            }
        }

        return score
    }

    private companion object {
        const val STARTING_SCORE = 0

        const val SCORE_PER_TOO_MUCH_CONSECUTIVE_SHIFT_REMOVED = -101
    }
}
