package pl.jakubgil.calculator.data.nurse.fitness

import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month

internal interface NurseFitnessScoreCalculation {

    fun calculate(month: Month, nurseMonthlySchedule: NurseMonthlySchedule): Int
}
