package pl.jakubgil.nurse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.jakubgil.nurse.domain.useCases.DeleteNurse
import pl.jakubgil.nurse.domain.useCases.GetAllNurses

class NurseListViewModel(getAllNurses: GetAllNurses, private val deleteNurse: DeleteNurse) : ViewModel() {

    private val _uiState = MutableStateFlow(NurseListUiState(nurses = emptyList()))
    val uiState: StateFlow<NurseListUiState> = _uiState.asStateFlow()

    init {
        getAllNurses()
            .onEach { list ->
                _uiState.value = NurseListUiState(nurses = list.map { it.toPresentation() })
            }
            .launchIn(viewModelScope)
    }

    fun deleteNurse(id: Long) = viewModelScope.launch {
        deleteNurse.invoke(id)
    }
}
