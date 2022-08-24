package me.project.moedadigital.ui.Coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.project.moedadigital.data.adapter.CoinAdapter
import me.project.moedadigital.data.api.CoinWebService
import me.project.moedadigital.data.repositories.CoinRepository
import me.project.moedadigital.databinding.FragmentCoinBinding

class CoinFragment : Fragment(){

    private var _binding: FragmentCoinBinding? = null

    private lateinit var viewModel: CoinViewModel

    private val webService = CoinWebService.getInstance()

    private lateinit var coinAdapter: CoinAdapter

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
                this,
                CoinViewModelFactory(CoinRepository(webService))
            )[CoinViewModel::class.java]

        _binding = FragmentCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurationRecycle()
        observeListCoin()

    }

    private fun configurationRecycle() {
        binding.rvCoin.layoutManager = LinearLayoutManager(context)
        binding.rvCoin.setHasFixedSize(true)
    }

    private fun observeListCoin() {
        viewModel.coinList.observe(viewLifecycleOwner) {
            coinAdapter = CoinAdapter(it)
            binding.rvCoin.adapter = coinAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}