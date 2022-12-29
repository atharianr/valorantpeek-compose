package id.atharianr.valorantpeek.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.atharianr.valorantpeek.data.AgentRepository
import id.atharianr.valorantpeek.ui.screen.detail.DetailViewModel
import id.atharianr.valorantpeek.ui.screen.favorite.FavoriteViewModel
import id.atharianr.valorantpeek.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: AgentRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}