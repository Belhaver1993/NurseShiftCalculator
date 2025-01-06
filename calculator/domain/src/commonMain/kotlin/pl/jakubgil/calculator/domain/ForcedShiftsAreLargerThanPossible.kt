package pl.jakubgil.calculator.domain

// TODO: Change nurseIndex to nurseId after implementing it
data class ForcedShiftsAreLargerThanPossible(val nurseIndex: Int, val input: ForcedShiftWrongInputException) :
    IllegalArgumentException("$input shifts are larger than possible")

enum class ForcedShiftWrongInputException {
    FULL, SHORT, EMPTY_SHIFT
}
