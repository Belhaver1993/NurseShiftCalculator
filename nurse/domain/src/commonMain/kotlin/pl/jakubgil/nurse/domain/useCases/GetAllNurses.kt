package pl.jakubgil.nurse.domain.useCases

import kotlinx.coroutines.flow.Flow
import pl.jakubgil.nurse.domain.NurseDatabaseRepository
import pl.jakubgil.nurse.domain.model.Nurse

class GetAllNurses(private val repository: NurseDatabaseRepository) {

    operator fun invoke(): Flow<List<Nurse>> = repository.getAllNurses()
}
