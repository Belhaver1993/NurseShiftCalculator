package pl.jakubgil.database.data

internal class DatabaseProvider(databaseDriverFactory: DatabaseDriverFactory) {

    private val database: NurseShiftCalculatorDatabase

    init {
        val driver = databaseDriverFactory.createDriver()
        database = NurseShiftCalculatorDatabase(driver)
    }

    fun provide(): NurseShiftCalculatorDatabase = database
}
