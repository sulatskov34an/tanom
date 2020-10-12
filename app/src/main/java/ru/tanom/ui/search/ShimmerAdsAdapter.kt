package ru.tanom.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tanom.R

class ShimmerAdsAdapter(private val shimmerAds: Array<Int>) :

    RecyclerView.Adapter<ShimmerAdsAdapter.ShimmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {

        return ShimmerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shimmer_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        holder.bind(shimmerAds[position])
    }

    override fun getItemCount() = shimmerAds.size

    class ShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Int) {
        }
    }
}