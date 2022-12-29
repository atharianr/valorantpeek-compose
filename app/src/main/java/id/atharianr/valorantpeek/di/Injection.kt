package id.atharianr.valorantpeek.di

import id.atharianr.valorantpeek.data.AgentRepository


object Injection {
    fun provideRepository(): AgentRepository {
        return AgentRepository.getInstance()
    }
}