package me.project.moedadigital.viewModel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.project.moedadigital.data.localData.AppDataBase
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.data.repositories.CoinRepository
import me.project.moedadigital.model.Coin

class CoinViewModel(application: Application) :
    AndroidViewModel(application) {

    private var repository: CoinRepository

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>> = _coinList

    private val _errorMessage = MutableLiveData<String>()
    //val errorMessage: LiveData<String> = _errorMessage

    init {
        getAllCoins()
        val coinDao = AppDataBase.getInstance(application).coinDao()
        repository = CoinRepository(coinDao)
    }

    val addFavorite: LiveData<List<CoinEntity>> = repository.readAllData


    private fun getAllCoins() {
        viewModelScope.launch {
            try {
                val coin = repository.getAllCoin()
                _coinList.postValue(coin)
            } catch (error: Throwable) {
                _errorMessage.postValue(error.message)
            }
        }

    }

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


    fun deleteCoin(coinEntity: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCoin(coinEntity)
        }

    }
}