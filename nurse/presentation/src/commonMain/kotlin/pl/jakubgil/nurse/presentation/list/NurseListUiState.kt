package pl.jakubgil.nurse.presentation.list

import pl.jakubgil.database.domain.model.Nurse

data class NurseListUiState(val nurses: List<NurseUiState>)

data class NurseUiState(val id: Long, val name: String, val surname: String)

fun Nurse.toPresentation() = NurseUiState(id, name, surname)
