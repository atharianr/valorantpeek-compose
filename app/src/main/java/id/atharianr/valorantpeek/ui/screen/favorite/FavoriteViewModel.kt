package id.atharianr.valorantpeek.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.atharianr.valorantpeek.data.AgentRepository
import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val agentRepository: AgentRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Agent>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Agent>>>
        get() = _uiState

    fun getFavoriteAgents() {
        viewModelScope.launch {
            agentRepository.getFavoriteAgents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { agents ->
                    _uiState.value = UiState.Success(agents)
                }
        }
    }
}