package pl.jakubgil.nurse.domain

import kotlinx.coroutines.flow.Flow
import pl.jakubgil.nurse.domain.model.Nurse

interface NurseDatabaseRepository {

    fun getAllNurses(): Flow<List<Nurse>>

    suspend fun createNurse(name: String, surname: String)

    suspend fun deleteNurse(id: Long)
}
