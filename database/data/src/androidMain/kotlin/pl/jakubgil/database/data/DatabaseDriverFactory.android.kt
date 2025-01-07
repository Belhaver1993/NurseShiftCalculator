package pl.jakubgil.database.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(NurseShiftCalculatorDatabase.Schema, context, "nurseShiftCalculator.db")
}
