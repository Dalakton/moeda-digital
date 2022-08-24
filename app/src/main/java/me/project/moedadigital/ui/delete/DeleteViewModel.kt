package me.project.moedadigital.ui.delete

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.project.moedadigital.data.localData.AppDataBase
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.data.repositories.FavoriteRepository

class DeleteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoriteRepository

    init {
        val coinDao = AppDataBase.getInstance(application).coinDao()
        repository = FavoriteRepository(coinDao)
    }

    fun deleteCoin(coinEntity: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCoin(coinEntity)
        }

    }
}