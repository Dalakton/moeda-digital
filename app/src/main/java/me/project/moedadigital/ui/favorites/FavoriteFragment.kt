package me.project.moedadigital.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.project.moedadigital.data.adapter.FavoriteAdapter
import me.project.moedadigital.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private lateinit var fViewModel: FavoriteViewModel


    private lateinit var favoriteAdapter: FavoriteAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fViewModel =
            ViewModelProvider(this)[FavoriteViewModel::class.java]



        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurationRecycle()
        observeListCoin()
        
    }

    private fun configurationRecycle() {
        binding.rvCoinFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvCoinFavorite.setHasFixedSize(true)
        favoriteAdapter = FavoriteAdapter()
    }

    private fun observeListCoin() {
        fViewModel.addFavorite.observe(viewLifecycleOwner) {
            favoriteAdapter.setData(it)
            binding.rvCoinFavorite.adapter = favoriteAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}