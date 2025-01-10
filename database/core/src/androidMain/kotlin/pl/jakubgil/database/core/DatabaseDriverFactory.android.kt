package pl.jakubgil.database.core

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

internal actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(NurseShiftCalculatorDatabase.Schema, context, "nurseShiftCalculator.db")
}
