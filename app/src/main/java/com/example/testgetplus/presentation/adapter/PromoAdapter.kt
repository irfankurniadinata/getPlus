package com.example.testgetplus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testgetplus.R
import com.example.testgetplus.data.model.Menu
import com.example.testgetplus.data.model.PromoDetail
import com.example.testgetplus.databinding.ItemViewMenuBinding
import com.example.testgetplus.databinding.ItemViewPromoBinding
import com.example.testgetplus.utils.extension.setImage

class PromoAdapter(var items: MutableList<PromoDetail>)
    : RecyclerView.Adapter<PromoAdapter.ViewHolder>() {

    var listener: AdapterClickListener<PromoDetail>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemViewPromoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_promo, parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(items: List<PromoDetail>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemViewPromoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PromoDetail) {
            binding.data = model

            binding.tvTitlePromo.text = "Promo ${model.order}"
            binding.ivPromo.setImage(model.imageUrl)

            itemView.setOnClickListener {
                listener?.onItemClick(model)
                notifyDataSetChanged()
            }
        }
    }
}