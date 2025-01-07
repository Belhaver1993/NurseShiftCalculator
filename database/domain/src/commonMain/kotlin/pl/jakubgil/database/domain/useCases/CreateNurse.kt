package pl.jakubgil.database.domain.useCases

import pl.jakubgil.database.domain.NurseDatabaseRepository

class CreateNurse(private val repository: NurseDatabaseRepository) {

    suspend fun invoke(name: String, surname: String) = repository.createNurse(name, surname)
}
