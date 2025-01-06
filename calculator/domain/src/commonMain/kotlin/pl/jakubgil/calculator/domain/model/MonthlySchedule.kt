package pl.jakubgil.calculator.domain.model

import pl.jakubgil.calculator.domain.model.violation.MonthlyScheduleScoreViolation

data class MonthlySchedule(val nurses: List<NurseMonthlySchedule>, var violations: List<MonthlyScheduleScoreViolation> = emptyList()) {

    fun fitnessScore(): Int = violations.sumOf { it.fitnessScore }
}
