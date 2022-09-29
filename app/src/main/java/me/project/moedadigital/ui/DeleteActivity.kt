package me.project.moedadigital.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.databinding.ActivityDeleteBinding
import me.project.moedadigital.viewModel.CoinViewModel
import java.text.NumberFormat

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(
                this
            )[CoinViewModel::class.java]


        getInformationPageDelete()

    }

    override fun onStart() {
        super.onStart()

        binding.buttonRemove.setOnClickListener {
            deleteCoinDb()
            finish()
        }
    }

    private fun getInformationPageDelete() {
        val coinEntity: CoinEntity = intent.getSerializableExtra("coinEntity") as CoinEntity
        binding.textBtc.text = coinEntity.moedaId
        binding.textValor.text = NumberFormat.getInstance().format(coinEntity.preco)
        binding.textValorHora.text = NumberFormat.getInstance().format(coinEntity.volumeUltimaHora)
        binding.textValorDia.text = NumberFormat.getInstance().format(coinEntity.volumeUltimaDia)
        binding.textValorMes.text = NumberFormat.getInstance().format(coinEntity.volumeUltimes)

        binding.imageBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        try {
            if (coinEntity.id_icon != null) {
                Glide.with(this)
                    .load(coinEntity.getPathUrlImage())
                    .centerCrop().into(binding.imagemCoin)
            }
        } catch (e: Exception) {
            binding.imagemCoin
        }
    }

    private fun deleteCoinDb() {
        val coinEntity: CoinEntity = intent.getSerializableExtra("coinEntity") as CoinEntity
        viewModel.deleteCoin(coinEntity)
        Toast.makeText(this, "Moeda Removida Com Sucesso", Toast.LENGTH_LONG).show()
    }
}