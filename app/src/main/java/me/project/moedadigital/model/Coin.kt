package me.project.moedadigital.model

import com.google.gson.annotations.SerializedName
import me.project.moedadigital.util.Constants
import java.io.Serializable


data class Coin(
    @SerializedName("asset_id")
    val moedaId: String,
    @SerializedName("name")
    val nomeMoeda: String,
    @SerializedName("price_usd")
    val preco: Double,
    @SerializedName("volume_1hrs_usd")
    val volumeUltimaHora: Double,
    @SerializedName("volume_1day_usd")
    val volumeUltimaDia: Double,
    @SerializedName("volume_1mth_usd")
    val volumeUltimes: Double,
    @SerializedName("url")
    val iconeImagem: String?,
    val id_icon: String?
) : Serializable {

    fun getPathUrlImage(): String {
        return Constants.BASE_URL_IMG.plus(getCoinFileName())
    }

    private fun getCoinFileName(): String {
        return "${id_icon?.replace("-", "")}.png"
    }


}

