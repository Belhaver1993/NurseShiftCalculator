package pl.jakubgil.calculator.data.nurse.fitness

import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseAmountOfConsecutiveShiftsScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseDayCannotBeAfterNightScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseShiftsShouldMatchWithPossibleShiftsScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseShortShiftShouldOnlyBeInWorkdayScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseSpecialShiftsAmountShouldBeAccurateScoreCalculation
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month

internal class NurseFitnessCalculator(
    private val nurseAmountOfConsecutiveShiftsScoreCalculation: NurseAmountOfConsecutiveShiftsScoreCalculation,
    private val nurseDayCannotBeAfterNightScoreCalculation: NurseDayCannotBeAfterNightScoreCalculation,
    private val nurseShiftsShouldMatchWithPossibleShiftsScoreCalculation: NurseShiftsShouldMatchWithPossibleShiftsScoreCalculation,
    private val nurseShortShiftShouldOnlyBeInWorkdayScoreCalculation: NurseShortShiftShouldOnlyBeInWorkdayScoreCalculation,
    private val nurseSpecialShiftsAmountShouldBeAccurateScoreCalculation: NurseSpecialShiftsAmountShouldBeAccurateScoreCalculation,
) {

    fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int {
        val calculations = listOf(
            nurseAmountOfConsecutiveShiftsScoreCalculation,
            nurseDayCannotBeAfterNightScoreCalculation,
            nurseShiftsShouldMatchWithPossibleShiftsScoreCalculation,
            nurseShortShiftShouldOnlyBeInWorkdayScoreCalculation,
            nurseSpecialShiftsAmountShouldBeAccurateScoreCalculation,
        )

        return calculations.sumOf { it.calculate(month, nurseMonthlySchedule) }
    }
}
