package pl.jakubgil.calculator.domain.model

data class NurseMonthlySchedule(
    // TODO: Change to nurse id
    val index: Int,
    val shifts: List<ShiftSchedule>,
    var fitnessScore: Int = Int.MIN_VALUE,
) {

    private val fullShifts = shifts.filter { it.shift == Shift.DAY || it.shift == Shift.NIGHT }
    private val nightShifts = shifts.filter { it.shift == Shift.NIGHT }
    private val shortShifts = shifts.filter { it.shift == Shift.SHORT }
    private val emptyShifts = shifts.filter { it.shift == Shift.EMPTY_SHIFT || it.shift == Shift.PLEASE_NO }
    private val vacations = shifts.filter { it.shift == Shift.VACATION }

    val numberOfFullShifts: Int = fullShifts.size
    val numberOfNightShifts: Int = nightShifts.size
    val numberOfShortShifts: Int = shortShifts.size
    val numberOfEmptyShifts: Int = emptyShifts.size
    val numberOfVacations: Int = vacations.size

    val numberOfForcedFullShifts: Int = fullShifts.filter { it.isForced }.size
    val numberOfForcedShortShifts: Int = shortShifts.filter { it.isForced }.size
    val numberOfForcedEmptyShifts: Int = emptyShifts.filter { it.isForced }.size
    val numberOfForcedVacations: Int = vacations.filter { it.isForced }.size
}
