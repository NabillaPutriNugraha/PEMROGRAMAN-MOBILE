package com.example.appleappxmlupgrade.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appleappxmlupgrade.databinding.ItemAppleBinding
import com.example.appleappxmlupgrade.feature.domain.Apple
import timber.log.Timber

class AppleAdapter(
    private val listApple: List<Apple>,
    private val onWebClick: (Apple) -> Unit,
    private val onDetailClick: (Apple) -> Unit
) : RecyclerView.Adapter<AppleAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemAppleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAppleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val apple = listApple[position]

        holder.binding.apply {
            imgItemPhoto.setImageResource(apple.imageRes)
            tvItemName.text = apple.name
            tvItemYear.text = apple.year
            tvItemDescription.text = apple.description

            btnLink.setOnClickListener {
                Timber.d("CCTV: User klik link web untuk ${apple.name}")
                onWebClick(apple)
            }

            btnDetail.setOnClickListener {
                Timber.d("CCTV: User klik detail untuk ${apple.name}")
                onDetailClick(apple)
            }

            root.setOnClickListener {
                Timber.d("CCTV: Item ${apple.name} diklik secara umum")
                onDetailClick(apple)
            }
        }
    }

    override fun getItemCount(): Int = listApple.size
}