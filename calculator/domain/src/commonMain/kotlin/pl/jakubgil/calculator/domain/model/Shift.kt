package pl.jakubgil.calculator.domain.model

enum class Shift(val representedLetter: Char) {
    EMPTY_SHIFT(' '),
    DAY('D'),
    NIGHT('N'),
    SHORT('S'),
    VACATION('V'),
    PLEASE_NO('P'),
    ;

    companion object {
        fun availableRandomShifts() = Shift.entries.filter { it != PLEASE_NO && it != VACATION }
    }
}
