package pl.jakubgil.database.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(NurseShiftCalculatorDatabase.Schema, "nurseShiftCalculator.db")
}
