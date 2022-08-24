package me.project.moedadigital.data.localData

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoin(coin: CoinEntity)

    @Query("SELECT * FROM coin_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<CoinEntity>>

    @Delete
    suspend fun deleteCoin(coin : CoinEntity)
}