package pl.jakubgil.nurse.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pl.jakubgil.nurse.domain.NurseDatabaseRepository
import pl.jakubgil.nurse.domain.model.Nurse

internal class NurseDatabaseRepositoryImpl(private val database: NurseShiftCalculatorDatabase) : NurseDatabaseRepository {

    override fun getAllNurses(): Flow<List<Nurse>> =
        database.nurseEntityQueries.getAllNurses().asFlow().mapToList(Dispatchers.IO).map { list ->
            list.map {
                Nurse(
                    id = it.id,
                    name = it.name,
                    surname = it.surname,
                )
            }
        }

    override suspend fun createNurse(name: String, surname: String) = withContext(Dispatchers.IO) {
        database.nurseEntityQueries.createNurse(name, surname)
    }

    override suspend fun deleteNurse(id: Long) = withContext(Dispatchers.IO) {
        database.nurseEntityQueries.deleteNurse(id)
    }
}
