package ru.tanom.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tanom.R

class ShimmerAdsAdapter : RecyclerView.Adapter<ShimmerAdsAdapter.ShimmerViewHolder>() {

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
    }

    override fun getItemCount() = 4

    class ShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}