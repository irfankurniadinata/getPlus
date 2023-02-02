package com.example.testgetplus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testgetplus.R
import com.example.testgetplus.data.model.MerchantDetail
import com.example.testgetplus.databinding.ItemViewMerchantBinding
import com.example.testgetplus.utils.extension.setImage

class MerchantAdapter(var items: MutableList<MerchantDetail>)
    : RecyclerView.Adapter<MerchantAdapter.ViewHolder>() {

    var listener: AdapterClickListener<MerchantDetail>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemViewMerchantBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_merchant, parent,
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

    fun setData(items: List<MerchantDetail>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemViewMerchantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MerchantDetail) {
            binding.data = model

            binding.ivMerchant.setImage(model.images?.feature?.imageUrl)
            binding.tvMerchant.text = model.name

            itemView.setOnClickListener {
                listener?.onItemClick(model)
                notifyDataSetChanged()
            }
        }
    }
}