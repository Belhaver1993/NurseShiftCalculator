package pl.jakubgil.calculator.data

import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

internal class GeneticGlobalProperties {

    val hoursPerDay: Duration = 7.hours.plus(35.minutes)

    val hoursPerFullShift: Duration = 12.hours

    val numberOfSpecialShiftsWithoutVacations: IntRange = 5..7

    val numberOfNursesPerWorkday: IntRange = 2..4

    val numberOfNursesPerSpecialShift: Int = 2
}
