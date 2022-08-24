package me.project.moedadigital.ui.Coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.project.moedadigital.data.repositories.CoinRepository
import me.project.moedadigital.model.Coin

class CoinViewModel(private val coinRepository: CoinRepository) :
    ViewModel() {

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>> = _coinList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        viewModelScope.launch {
            try {
                val coin = coinRepository.getAllCoin()
                _coinList.postValue(coin)
            } catch (error: Throwable) {
                _errorMessage.postValue(error.message)
            }
        }

    }
}