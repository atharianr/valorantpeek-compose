package id.atharianr.valorantpeek.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.atharianr.valorantpeek.data.AgentRepository
import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val agentRepository: AgentRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Agent>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Agent>>
        get() = _uiState

    fun getAgentById(agentId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(agentRepository.getAgentById(agentId))
        }
    }

    fun updateAgentById(agentId: Long, favorite: Boolean) {
        viewModelScope.launch {
            agentRepository.updateAgentById(agentId, favorite)
        }
    }
}