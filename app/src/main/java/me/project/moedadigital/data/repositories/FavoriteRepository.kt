package me.project.moedadigital.data.repositories

import androidx.lifecycle.LiveData
import me.project.moedadigital.data.localData.CoinDao
import me.project.moedadigital.data.localData.CoinEntity

class FavoriteRepository(private val coinDao: CoinDao) {

    suspend fun addCoin(coinEntity: CoinEntity){
        coinDao.addCoin(coinEntity)
    }

    val readAllData : LiveData<List<CoinEntity>> = coinDao.readAllData()


    suspend fun deleteCoin(coin: CoinEntity){
        coinDao.deleteCoin(coin)
    }

}