package pl.jakubgil.database.core

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

internal actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(NurseShiftCalculatorDatabase.Schema, "nurseShiftCalculator.db")
}
