package ru.tanom.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ads.view.*
import ru.tanom.R
import ru.tanom.data.model.Ads

class AdsAdapter(private val listener: (Ads) -> Unit) :
    RecyclerView.Adapter<AdsAdapter.ViewHolder>() {

    private val ads = mutableListOf<Ads>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ads,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ads[position], listener)
    }

    override fun getItemCount() = ads.size

    fun setData(items: List<Ads>) {
        ads.clear()
        ads.addAll(items)
        notifyDataSetChanged()
    }

    fun getList() = ads

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(ads: Ads, listener: (Ads) -> Unit) {
            itemView.title.text = ads.title
            itemView.setOnClickListener {
                listener(ads)
            }
        }
    }
}