package pl.jakubgil.database.domain

import kotlinx.coroutines.flow.Flow
import pl.jakubgil.database.domain.model.Nurse

interface NurseDatabaseRepository {

    fun getAllNurses(): Flow<List<Nurse>>

    suspend fun createNurse(name: String, surname: String)

    suspend fun deleteNurse(id: Long)
}
