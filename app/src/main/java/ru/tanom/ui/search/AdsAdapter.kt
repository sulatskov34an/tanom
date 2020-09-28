package ru.tanom.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ads.view.*
import ru.tanom.R
import ru.tanom.common.TimeUtils
import ru.tanom.common.getProgressBar
import ru.tanom.model.network.dto.Ad

class AdsAdapter(private val listener: (Ad) -> Unit) :
    RecyclerView.Adapter<AdsAdapter.ViewHolder>() {

    private val ads = mutableListOf<Ad>()

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

    fun setData(items: List<Ad>) {
        ads.clear()
        ads.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bind(ad: Ad, listener: (Ad) -> Unit) {
            itemView.description.text = "${ad.carFactory} ${ad.carModel}, ${ad.productionYear}"
            itemView.image?.apply {
                val path = "non"
                Picasso.get()
                    .load(path)
                    .error(R.drawable.ic_error)
                    .placeholder(getProgressBar(context))
                    .into(itemView.image)
            }
            itemView.setOnClickListener {
                listener(ad)
            }
            itemView.price.text = "${ad.price} ₽"
            itemView.inspectionPlace.text = ad.inspectionPlace
            itemView.date.text =
                TimeUtils(itemView.context).getDateWithInTime(itemView.context, ad.creationDate)
        }
    }
}