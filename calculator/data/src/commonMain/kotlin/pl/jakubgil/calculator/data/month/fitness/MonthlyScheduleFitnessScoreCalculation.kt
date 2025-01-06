package pl.jakubgil.calculator.data.month.fitness

import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.violation.MonthlyScheduleScoreViolation
import pl.jakubgil.calendar.domain.model.Month

internal interface MonthlyScheduleFitnessScoreCalculation {

    fun calculate(month: Month, monthlySchedule: MonthlySchedule): List<MonthlyScheduleScoreViolation>
}
