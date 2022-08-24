package me.project.moedadigital.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.project.moedadigital.databinding.ActivityDetailBinding
import me.project.moedadigital.model.Coin
import me.project.moedadigital.ui.favorites.FavoriteViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var mViewModel: FavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel =
            ViewModelProvider(
                this,
            )[FavoriteViewModel::class.java]

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
        binding.textValor.text = coin.preco.toString()
        binding.textValorHora.text = coin.volumeUltimaHora.toString()
        binding.textValorDia.text = coin.volumeUltimaDia.toString()
        binding.textValorMes.text = coin.volumeUltimes.toString()
        binding.textBack.setOnClickListener {
            finish()
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
            mViewModel.addCoin(coin)
            Toast.makeText(this, "Moeda Adicionada Com Sucesso", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Erro Inesperado", Toast.LENGTH_SHORT).show()
        }
    }
}