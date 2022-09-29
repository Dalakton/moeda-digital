package me.project.moedadigital.data.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.project.moedadigital.data.localData.CoinEntity
import me.project.moedadigital.databinding.ItemCoinFavoriteBinding
import me.project.moedadigital.ui.DeleteActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var coinList = emptyList<CoinEntity>()

    class MyViewHolder(val binding: ItemCoinFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCoinFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.itemView.context
        val coinEntity = coinList[position]

        holder.binding.textCoinName.text = coinEntity.nomeMoeda
        holder.binding.textSiglaCoin.text = coinEntity.moedaId
        holder.binding.textPriceCoin.text = coinEntity.preco.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DeleteActivity::class.java)
            intent.putExtra("coinEntity", coinEntity)
            context.startActivity(intent)
        }


        try {
            if (coinEntity.id_icon != null) {
                Glide.with(context)
                    .load(coinEntity.getPathUrlImage())
                    .centerCrop().into(holder.binding.imageCoinFavorite)
            }
        } catch (e: Exception) {
            holder.binding.imageCoinFavorite
        }

    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(coin: List<CoinEntity>) {
        this.coinList = coin
        notifyDataSetChanged()
    }
}