package pl.jakubgil.calculator.data.nurse.fitness.calculations

import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessScoreCalculation
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calendar.domain.model.DayType
import pl.jakubgil.calendar.domain.model.Month

internal class NurseShortShiftShouldOnlyBeInWorkdayScoreCalculation : NurseFitnessScoreCalculation {

    override fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        nurseMonthlySchedule.shifts.forEachIndexed { index, shiftSchedule ->
            if (shiftSchedule.shift == Shift.SHORT && !shiftSchedule.isForced && month.days[index].type != DayType.WORKDAY) {
                return SCORE_FOR_VIOLATION
            }
        }

        return 0
    }

    private companion object {
        const val SCORE_FOR_VIOLATION = -104
    }
}
