package me.project.moedadigital.data.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.project.moedadigital.data.api.CoinWebService
import me.project.moedadigital.data.localData.CoinDao
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.model.Coin

class CoinRepository(private val coinDao: CoinDao, private val coinWebService : CoinWebService ) {


    suspend fun getAllCoin(): List<Coin> {
        return withContext(Dispatchers.IO) {
            coinWebService.getAllCoins()
        }
    }

    suspend fun addCoin(coinEntity: CoinEntity) {
        coinDao.addCoin(coinEntity)
    }

    val readAllData: LiveData<List<CoinEntity>> = coinDao.readAllData()


    suspend fun deleteCoin(coin: CoinEntity) {
        coinDao.deleteCoin(coin)
    }


}