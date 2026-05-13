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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appleappxmlupgrade.R
import com.example.appleappxmlupgrade.databinding.FragmentHomeBinding
import com.example.appleappxmlupgrade.feature.domain.Apple
import com.example.appleappxmlupgrade.feature.presentation.adapter.AppleAdapter
import com.example.appleappxmlupgrade.feature.presentation.adapter.FeaturedAdapter
import com.example.appleappxmlupgrade.feature.presentation.viewmodel.MainViewModel
import com.example.appleappxmlupgrade.feature.presentation.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory("Apple Store Home") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        observeNavigation()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.appleState.collect { list ->
                    if (list.isNotEmpty()) {
                        setupFeaturedRecycler(list.take(3))
                        setupAllRecycler(list)
                    }
                }
            }
        }
    }

    private fun observeNavigation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigateToDetail.collect { id ->
                    id?.let {
                        moveToDetail(it)
                        viewModel.onNavigated()
                    }
                }
            }
        }
    }

    private fun setupFeaturedRecycler(list: List<Apple>) {
        val featuredAdapter = FeaturedAdapter(
            list,
            onWebClick = {
                Timber.d("CCTV: Klik Web di Featured: ${it.name}")
                openWeb(it.webUrl)
            },
            onDetailClick = {
                viewModel.onDetailClicked(it.id)
            }
        )
        binding.rvFeatured.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = featuredAdapter
        }
    }

    private fun setupAllRecycler(list: List<Apple>) {
        val allAdapter = AppleAdapter(
            list,
            onWebClick = {
                Timber.d("CCTV: Klik Web di List: ${it.name}")
                openWeb(it.webUrl)
            },
            onDetailClick = {
                viewModel.onDetailClicked(it.id)
            }
        )
        binding.rvApple.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun openWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun moveToDetail(id: Int) {
        val selectedItem = viewModel.appleState.value.find { it.id == id }
        Timber.d("CCTV: Navigasi dijalankan untuk: ${selectedItem?.name}")

        val bundle = Bundle().apply { putInt("EXTRA_ID", id) }
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}