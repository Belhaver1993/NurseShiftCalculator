package pl.jakubgil.calculator.data

import pl.jakubgil.calculator.data.month.MonthlyScheduleCreator
import pl.jakubgil.calculator.domain.CalculatorRepository
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month

internal class CalculatorRepositoryImpl(private val monthlyScheduleCreator: MonthlyScheduleCreator) : CalculatorRepository {

    override suspend fun calculateShifts(month: Month, preconditions: List<NurseMonthlySchedule>): MonthlySchedule =
        monthlyScheduleCreator.create(
            month,
            preconditions,
        )
}
