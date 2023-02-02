package com.example.testgetplus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testgetplus.R
import com.example.testgetplus.data.model.VoucherDetail
import com.example.testgetplus.databinding.ItemViewVoucherBinding
import com.example.testgetplus.utils.extension.setImage
import java.text.SimpleDateFormat
import java.util.*

class VoucherAdapter(var items: MutableList<VoucherDetail>)
    : RecyclerView.Adapter<VoucherAdapter.ViewHolder>() {

    var listener: AdapterClickListener<VoucherDetail>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemViewVoucherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_voucher, parent,
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

    fun setData(items: List<VoucherDetail>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemViewVoucherBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: VoucherDetail) {
            binding.data = model

            itemView.setOnClickListener {
                listener?.onItemClick(model)
                notifyDataSetChanged()
            }

            val date = model.validUntil
            binding.ivVoucher.setImage(model.images?.feature?.imageUrl)

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            val d: Date = sdf.parse(date)
            val formattedTime = output.format(d)
            binding.tvExpiredDate.text = "Valid Until : $formattedTime"
        }
    }
}