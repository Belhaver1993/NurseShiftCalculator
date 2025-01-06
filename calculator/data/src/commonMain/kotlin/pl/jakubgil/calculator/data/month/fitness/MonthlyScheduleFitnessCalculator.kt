package pl.jakubgil.calculator.data.month.fitness

import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.violation.MonthlyScheduleScoreViolation
import pl.jakubgil.calendar.domain.model.Month

internal class MonthlyScheduleFitnessCalculator(
    private val amountOfNursesPerDayScoreCalculation: MonthAmountOfNursesPerDayScoreCalculation,
    private val specialShiftsShouldBeEqualToRequiredAmountScoreCalculation: MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation,
) {

    fun calculate(month: Month, monthlySchedule: MonthlySchedule): List<MonthlyScheduleScoreViolation> {
        val calculations = listOf(
            amountOfNursesPerDayScoreCalculation,
            specialShiftsShouldBeEqualToRequiredAmountScoreCalculation,
        )

        val violations = calculations.flatMap { it.calculate(month, monthlySchedule) }

        return violations
    }
}
