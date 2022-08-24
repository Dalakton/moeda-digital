package me.project.moedadigital.ui.delete

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding

    private lateinit var dViewModel: DeleteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dViewModel =
            ViewModelProvider(this)[DeleteViewModel::class.java]

        getInformationPageDelete()

    }

    override fun onStart() {
        super.onStart()

        binding.buttonRemove.setOnClickListener {
            deleteCoinDb()
        }
    }

    private fun getInformationPageDelete() {
        val coinEntity: CoinEntity = intent.getSerializableExtra("coinEntity") as CoinEntity
        binding.textBtc.text = coinEntity.moedaId
        binding.textValor.text = coinEntity.preco.toString()
        binding.textValorHora.text = coinEntity.volumeUltimaHora.toString()
        binding.textValorDia.text = coinEntity.volumeUltimaDia.toString()
        binding.textValorMes.text = coinEntity.volumeUltimes.toString()
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
        dViewModel.deleteCoin(coinEntity)
        Toast.makeText(this, "Moeda Removida Com Sucesso", Toast.LENGTH_LONG).show()


    }
}