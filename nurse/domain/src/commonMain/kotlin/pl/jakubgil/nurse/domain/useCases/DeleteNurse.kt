package pl.jakubgil.nurse.domain.useCases

import pl.jakubgil.nurse.domain.NurseDatabaseRepository

class DeleteNurse(private val repository: NurseDatabaseRepository) {

    suspend operator fun invoke(id: Long) = repository.deleteNurse(id)
}
