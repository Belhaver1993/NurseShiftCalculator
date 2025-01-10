package pl.jakubgil.nurse.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.jakubgil.nurse.domain.useCases.CreateNurse

class NurseCreateViewModel(private val createNurse: CreateNurse) : ViewModel() {

    private val _uiState = MutableStateFlow(NurseCreateUiState())
    val uiState: StateFlow<NurseCreateUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateSurname(surname: String) {
        _uiState.value = _uiState.value.copy(surname = surname)
    }

    fun createNurse() = viewModelScope.launch {
        val state = _uiState.value
        createNurse.invoke(state.name.trim(), state.surname.trim())
        _uiState.value = _uiState.value.copy(nurseCreated = true)
    }
}
