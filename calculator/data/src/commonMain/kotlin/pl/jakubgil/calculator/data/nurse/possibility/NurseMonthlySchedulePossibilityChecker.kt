package pl.jakubgil.calculator.data.nurse.possibility

import pl.jakubgil.calculator.domain.ForcedShiftWrongInputException
import pl.jakubgil.calculator.domain.ForcedShiftsAreLargerThanPossible
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calculator.domain.model.ShiftSchedule
import pl.jakubgil.calendar.domain.model.Month

internal class NurseMonthlySchedulePossibilityChecker(private val possibleShiftsCreator: NursePossibleShiftsCreator) {

    fun isSchedulePossible(nurseIndex: Int, month: Month, preconditions: List<ShiftSchedule>) {
        val possibleShifts = possibleShiftsCreator.create(month, preconditions.filter { it.shift == Shift.VACATION }.size)

        val numberOfForcedFullShifts = preconditions.filter { it.shift == Shift.DAY || it.shift == Shift.NIGHT }.filter { it.isForced }.size
        if (numberOfForcedFullShifts > possibleShifts.numberOfFullShifts) {
            throw ForcedShiftsAreLargerThanPossible(nurseIndex = nurseIndex, ForcedShiftWrongInputException.FULL)
        }

        val numberOfForcedShortShifts = preconditions.filter { it.shift == Shift.SHORT }.filter { it.isForced }.size
        if (numberOfForcedShortShifts > possibleShifts.numberOfShortShifts) {
            throw ForcedShiftsAreLargerThanPossible(nurseIndex = nurseIndex, ForcedShiftWrongInputException.SHORT)
        }

        val numberOfForcedEmptyShifts = preconditions.filter {
            it.shift == Shift.EMPTY_SHIFT || it.shift == Shift.PLEASE_NO
        }.filter { it.isForced }.size
        if (numberOfForcedEmptyShifts > possibleShifts.numberOfEmptyShifts) {
            throw ForcedShiftsAreLargerThanPossible(nurseIndex = nurseIndex, ForcedShiftWrongInputException.EMPTY_SHIFT)
        }
    }
}
