package pl.jakubgil.calculator.domain.model

data class ShiftSchedule(val shift: Shift, val isForced: Boolean = false) {

    override fun toString(): String = "${shift.representedLetter}"
}
