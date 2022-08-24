package me.project.moedadigital.ui.Coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.project.moedadigital.data.repositories.CoinRepository

class CoinViewModelFactory(private val repository: CoinRepository): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
        CoinViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Viewmodel not found")
        }
    }
}