package pl.jakubgil.calculator.data.month.fitness.calculations

import pl.jakubgil.calculator.data.GeneticGlobalProperties
import pl.jakubgil.calculator.data.month.fitness.MonthlyScheduleFitnessScoreCalculation
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation.Companion.NIGHT_SHIFT_AMOUNT_IS_WRONG
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation.Companion.SPECIAL_DAY_SHIFT_AMOUNT_IS_WRONG
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calculator.domain.model.violation.MonthlyScheduleScoreViolation
import pl.jakubgil.calendar.domain.model.DayType
import pl.jakubgil.calendar.domain.model.Month

internal class MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation(private val globalProperties: GeneticGlobalProperties) :
    MonthlyScheduleFitnessScoreCalculation {

    override fun calculate(
        month: Month,
        monthlySchedule: MonthlySchedule,
    ): List<MonthlyScheduleScoreViolation> {
        val violations = mutableListOf<MonthlyScheduleScoreViolation>()

        val specialDays =
            month.days.filter {
                it.type == DayType.SATURDAY ||
                    it.type == DayType.SUNDAY ||
                    it.type == DayType.HOLIDAY ||
                    it.type == DayType.HOLIDAY_AND_SUNDAY
            }
        val specialDayShiftAmount = specialDays
            .map { it.dayOfMonth - 1 }
            .sumOf { dayIndex ->
                monthlySchedule.nurses.sumOf {
                    if (it.shifts[dayIndex].shift == Shift.DAY) {
                        1
                    } else {
                        0
                    }.toInt()
                }
            }

        if (specialDays.size * globalProperties.numberOfNursesPerSpecialShift != specialDayShiftAmount) {
            violations += SpecialDayShiftAmountIsWrong()
        }

        val nightShiftAmount = monthlySchedule.nurses.sumOf {
            it.shifts.sumOf {
                if (it.shift == Shift.NIGHT) {
                    1
                } else {
                    0
                }.toInt()
            }
        }

        if (month.numberOfDays() * globalProperties.numberOfNursesPerSpecialShift != nightShiftAmount) {
            violations += NightShiftAmountIsWrong()
        }

        return violations
    }

    companion object {
        const val SPECIAL_DAY_SHIFT_AMOUNT_IS_WRONG = -100000
        const val NIGHT_SHIFT_AMOUNT_IS_WRONG = -100001
    }
}

data class SpecialDayShiftAmountIsWrong(override val fitnessScore: Int = SPECIAL_DAY_SHIFT_AMOUNT_IS_WRONG) : MonthlyScheduleScoreViolation

data class NightShiftAmountIsWrong(override val fitnessScore: Int = NIGHT_SHIFT_AMOUNT_IS_WRONG) : MonthlyScheduleScoreViolation
