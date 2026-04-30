package com.example.appleappxml.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appleappxml.data.AppleSeries
import com.example.appleappxml.databinding.ItemAppleBinding

class AppleAdapter(
    private val listApple: List<AppleSeries>,
    private val onItemClick: (AppleSeries) -> Unit,
    private val onDetailClick: (AppleSeries) -> Unit
) : RecyclerView.Adapter<AppleAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemAppleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAppleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = listApple[position]

        holder.binding.apply {
            imgItemPhoto.setImageResource(product.imageRes)
            tvItemName.text = product.name
            tvItemYear.text = product.year

            root.setOnClickListener { onItemClick(product) }

            btnDetail.setOnClickListener { onDetailClick(product) }

            btnLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.webUrl))
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listApple.size
}