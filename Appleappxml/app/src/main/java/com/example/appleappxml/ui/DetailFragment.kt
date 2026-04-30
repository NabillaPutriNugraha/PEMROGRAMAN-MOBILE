package com.example.appleappxml.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appleappxml.data.AppleSeries
import com.example.appleappxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("EXTRA_ID") ?: 0
        val product = AppleSeries.getData().find { it.id == id }

        product?.let { data ->
            binding.apply {
                imgDetail.setImageResource(data.imageRes)
                tvDetailName.text = data.name
                tvDetailYear.text = data.year
                tvLabelSpecs.text = data.specsLabel
                tvValueSpecs.text = data.specsValue

                btnOpenWeb.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.webUrl))
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}