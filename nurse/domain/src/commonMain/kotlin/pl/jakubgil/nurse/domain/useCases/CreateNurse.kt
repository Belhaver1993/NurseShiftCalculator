package pl.jakubgil.nurse.domain.useCases

import pl.jakubgil.nurse.domain.NurseDatabaseRepository

class CreateNurse(private val repository: NurseDatabaseRepository) {

    suspend operator fun invoke(name: String, surname: String) = repository.createNurse(name, surname)
}
