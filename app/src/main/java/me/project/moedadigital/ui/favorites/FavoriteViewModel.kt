package me.project.moedadigital.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.project.moedadigital.data.localData.AppDataBase
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.data.repositories.FavoriteRepository
import me.project.moedadigital.model.Coin

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : FavoriteRepository

    init {
        val coinDao = AppDataBase.getInstance(application).coinDao()
        repository = FavoriteRepository(coinDao)
    }

    val addFavorite: LiveData<List<CoinEntity>> = repository.readAllData

    fun addCoin(coin: Coin) {
        val coinEntity = CoinEntity(
            nomeMoeda = coin.nomeMoeda,
            moedaId = coin.moedaId,
            preco = coin.preco,
            iconeImagem = coin.iconeImagem,
            id = 0,
            id_icon = coin.id_icon,
            volumeUltimaHora = coin.volumeUltimaHora,
            volumeUltimaDia = coin.volumeUltimaDia,
            volumeUltimes = coin.volumeUltimes
        )
        viewModelScope.launch(Dispatchers.IO) {
           repository.addCoin(coinEntity)
        }
    }
}