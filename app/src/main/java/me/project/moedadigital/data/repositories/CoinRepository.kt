package me.project.moedadigital.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.project.moedadigital.data.api.CoinWebService
import me.project.moedadigital.model.Coin

class CoinRepository(private val coinWebService: CoinWebService) {

    suspend fun getAllCoin(): List<Coin> {
        return withContext(Dispatchers.IO) {
            coinWebService.getAllCoins()
        }
    }


}