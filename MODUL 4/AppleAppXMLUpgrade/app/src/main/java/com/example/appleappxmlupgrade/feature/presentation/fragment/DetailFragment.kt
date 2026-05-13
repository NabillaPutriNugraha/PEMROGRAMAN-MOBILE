package com.example.appleappxmlupgrade.feature.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.appleappxmlupgrade.databinding.FragmentDetailBinding
import com.example.appleappxmlupgrade.feature.domain.Apple
import com.example.appleappxmlupgrade.feature.presentation.viewmodel.MainViewModel
import com.example.appleappxmlupgrade.feature.presentation.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory("Apple Detail Page") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("EXTRA_ID") ?: -1
        Timber.d("CCTV: DetailFragment menerima ID: $productId")

        observeDetailData(productId)
    }

    private fun observeDetailData(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.appleState.collect { list ->
                    val product = list.find { it.id == id }

                    product?.let { data ->
                        Timber.d("CCTV: Menampilkan detail produk: Name=${data.name}, Year=${data.year}")
                        setupUI(data)
                    }
                }
            }
        }
    }

    private fun setupUI(data: Apple) {
        binding.apply {
            imgDetail.setImageResource(data.imageRes)
            tvDetailName.text = data.name
            tvDetailYear.text = data.year
            tvDetailDescription.text = data.description
            tvLabelSpecs.text = data.specsLabel
            tvValueSpecs.text = data.specsValue

            btnOpenWeb.setOnClickListener {
                Timber.d("CCTV: Tombol Web ditekan untuk ${data.name}")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.webUrl))
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}