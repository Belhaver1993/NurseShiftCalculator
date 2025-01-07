package pl.jakubgil.calculator.domain.useCases

import pl.jakubgil.calculator.domain.CalculatorRepository
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month

class CalculateShift(private val calculatorRepository: CalculatorRepository) {

    suspend fun calculate(month: Month, preconditions: List<NurseMonthlySchedule>): MonthlySchedule = calculatorRepository.calculateShifts(
        month,
        preconditions,
    )
}
