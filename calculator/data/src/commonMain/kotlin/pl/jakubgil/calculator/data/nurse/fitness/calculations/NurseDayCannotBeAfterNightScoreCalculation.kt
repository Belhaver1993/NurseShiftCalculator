package pl.jakubgil.calculator.data.nurse.fitness.calculations

import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessScoreCalculation
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calendar.domain.model.Month

internal class NurseDayCannotBeAfterNightScoreCalculation : NurseFitnessScoreCalculation {

    override fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        var score = STARTING_SCORE

        nurseMonthlySchedule.shifts.forEachIndexed { index, shiftSchedule ->
            val isNotLastIndex = index < nurseMonthlySchedule.shifts.size - 1
            val isNight = shiftSchedule.shift == Shift.NIGHT
            if (isNotLastIndex && isNight) {
                val isNextShiftDay = nurseMonthlySchedule.shifts[index + 1].shift == Shift.DAY
                if (isNextShiftDay) {
                    score += SCORE_PER_DAY_AFTER_NIGHT_REMOVED
                }
            }
        }

        return score
    }

    private companion object {
        const val STARTING_SCORE = 0

        const val SCORE_PER_DAY_AFTER_NIGHT_REMOVED = -103
    }
}
