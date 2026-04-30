package com.example.appleappxml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appleappxml.R
import com.example.appleappxml.databinding.FragmentHomeBinding
import com.example.appleappxml.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.appleList.observe(viewLifecycleOwner) { list ->
            showRecyclerList(list)
        }
    }

    private fun showRecyclerList(list: List<com.example.appleappxml.data.AppleSeries>) {
        binding.rvApple.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AppleAdapter(
            list,
            onItemClick = { product ->
                moveToDetail(product.id)
            },
            onDetailClick = { product ->
                moveToDetail(product.id)
            }
        )
        binding.rvApple.adapter = adapter
    }

    private fun moveToDetail(productId: Int) {
        val bundle = Bundle()
        bundle.putInt("EXTRA_ID", productId)

        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}