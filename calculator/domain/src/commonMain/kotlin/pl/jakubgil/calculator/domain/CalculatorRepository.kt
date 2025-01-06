package pl.jakubgil.calculator.domain

import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month

interface CalculatorRepository {

    suspend fun calculateShifts(month: Month, preconditions: List<NurseMonthlySchedule>): MonthlySchedule
}
