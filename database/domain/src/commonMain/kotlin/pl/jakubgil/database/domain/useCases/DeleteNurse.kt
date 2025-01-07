package pl.jakubgil.database.domain.useCases

import pl.jakubgil.database.domain.NurseDatabaseRepository

class DeleteNurse(private val repository: NurseDatabaseRepository) {

    suspend fun invoke(id: Long) = repository.deleteNurse(id)
}
