package me.project.moedadigital.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.project.moedadigital.data.adapter.FavoriteAdapter
import me.project.moedadigital.databinding.FragmentFavoriteBinding
import me.project.moedadigital.viewModel.CoinViewModel
import java.text.SimpleDateFormat
import java.util.*

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private lateinit var viewModel: CoinViewModel

    private lateinit var favoriteAdapter: FavoriteAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel =
            ViewModelProvider(
                this
            )[CoinViewModel::class.java]


        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateFormat()
        configurationRecycle()
        getFavoriteCoin()

    }

    private fun dateFormat() {
        val date = Calendar.getInstance().time
        val dateTime = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        binding.textData.text = dateTime.format(date)
    }

    private fun configurationRecycle() {
        binding.rvCoinFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvCoinFavorite.setHasFixedSize(true)
        favoriteAdapter = FavoriteAdapter()
    }

    private fun getFavoriteCoin() {
        viewModel.addFavorite.observe(viewLifecycleOwner) {
            favoriteAdapter.setData(it)
            binding.rvCoinFavorite.adapter = favoriteAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}