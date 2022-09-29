package me.project.moedadigital.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.project.moedadigital.databinding.ActivityDetailBinding
import me.project.moedadigital.model.Coin
import me.project.moedadigital.viewModel.CoinViewModel
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(
                this
            )[CoinViewModel::class.java]

        getDetails()
    }

    override fun onStart() {
        super.onStart()

        binding.buttonAdd.setOnClickListener {
            insertDataToBase()
        }
    }


    private fun getDetails() {
        val coin: Coin = intent.getSerializableExtra("coin") as Coin
        binding.textBtc.text = coin.moedaId
        binding.textValor.text = NumberFormat.getInstance().format(coin.preco)
        binding.textValorHora.text = NumberFormat.getInstance().format(coin.volumeUltimaHora)
        binding.textValorDia.text = NumberFormat.getInstance().format(coin.volumeUltimaDia)
        binding.textValorMes.text = NumberFormat.getInstance().format(coin.volumeUltimes)

        binding.imageBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        try {
            if (coin.id_icon != null) {
                Glide.with(this)
                    .load(coin.getPathUrlImage())
                    .centerCrop().into(binding.imagemCoin)
            }
        } catch (e: Exception) {
            binding.imagemCoin
        }

    }

    private fun insertDataToBase() {
        try {
            val coin: Coin = intent.getSerializableExtra("coin") as Coin
            viewModel.addCoin(coin)
            Toast.makeText(this, "Moeda Adicionada Com Sucesso", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Erro Inesperado", Toast.LENGTH_SHORT).show()
        }
    }
}