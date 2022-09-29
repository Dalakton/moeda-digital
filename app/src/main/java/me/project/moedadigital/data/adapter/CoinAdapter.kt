package me.project.moedadigital.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.project.moedadigital.databinding.ItemCoinBinding
import me.project.moedadigital.model.Coin
import me.project.moedadigital.ui.DetailActivity
import java.text.NumberFormat

class CoinAdapter(private val coins: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemCoinBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val coin = coins[position]

        holder.binding.textNomeMoeda.text = coin.nomeMoeda
        holder.binding.textValor.text = NumberFormat.getInstance().format(coin.preco)
        holder.binding.textSiglaCoin.text = coin.moedaId

        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("coin", coin)
            context.startActivity(intent)
        }

        try {
            if (coin.id_icon != null) {
                Glide.with(context)
                    .load(coin.getPathUrlImage())
                    .centerCrop().into(holder.binding.imagemCoin)
            }
        } catch (e: Exception) {
            holder.binding.imagemCoin
        }

    }

    override fun getItemCount(): Int {
        return coins.size
    }

    class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root)
}
