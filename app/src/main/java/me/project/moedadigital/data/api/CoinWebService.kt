package me.project.moedadigital.data.api

import me.project.moedadigital.model.Coin
import me.project.moedadigital.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoinWebService {

    @GET(Constants.ENDPOINT)
    suspend fun getAllCoins(): List<Coin>

    companion object {

        private val coinWebService: CoinWebService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(CoinWebService::class.java)

        }

        fun getInstance(): CoinWebService {
            return coinWebService
        }
    }

}