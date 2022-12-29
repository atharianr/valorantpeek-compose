package id.atharianr.valorantpeek.data

import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.model.FakeAgentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AgentRepository {
    private val agentList = mutableListOf<Agent>()

    init {
        if (agentList.isEmpty()) {
            agentList.addAll(FakeAgentDataSource.dummyAgents)
        }
    }

    fun getAllAgents(): Flow<List<Agent>> = flowOf(agentList)

    fun getQueryAgents(query: String): Flow<List<Agent>> =
        flowOf(agentList.filter { it.name.contains(query, ignoreCase = true) })

    fun getFavoriteAgents(): Flow<List<Agent>> = flowOf(agentList.filter { it.isFavorite })

    fun getAgentById(agentId: Long): Agent = agentList.first { it.id == agentId }

    fun updateAgentById(agentId: Long, favorite: Boolean): Flow<Boolean> {
        val index = agentList.indexOfFirst { it.id == agentId }
        val result = if (index >= 0) {
            val agents = agentList[index]
            agentList[index] = agents.copy(isFavorite = favorite)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: AgentRepository? = null

        fun getInstance(): AgentRepository =
            instance ?: synchronized(this) {
                AgentRepository().apply {
                    instance = this
                }
            }
    }
}