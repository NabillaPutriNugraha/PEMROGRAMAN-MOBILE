package com.example.appleappxmlupgrade.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appleappxmlupgrade.databinding.ItemFeaturedAppleBinding
import com.example.appleappxmlupgrade.feature.domain.Apple

class FeaturedAdapter(
    private val listApple: List<Apple>,
    private val onWebClick: (Apple) -> Unit,
    private val onDetailClick: (Apple) -> Unit
) : RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFeaturedAppleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeaturedAppleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apple = listApple[position]
        holder.binding.apply {
            imgFeaturedPhoto.setImageResource(apple.imageRes)
            tvFeaturedName.text = apple.name
            tvFeaturedDescription.text = apple.description

            btnFeaturedWeb.setOnClickListener { onWebClick(apple) }
            btnFeaturedDetail.setOnClickListener { onDetailClick(apple) }
            root.setOnClickListener { onDetailClick(apple) }
        }
    }

    override fun getItemCount(): Int = listApple.size
}