package com.example.testgetplus.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testgetplus.R
import com.example.testgetplus.data.model.Menu
import com.example.testgetplus.databinding.ItemViewMenuBinding
import com.example.testgetplus.utils.extension.setImage

class MenuAdapter(var items: MutableList<Menu>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var listener: AdapterClickListener<Menu>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemViewMenuBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_menu, parent,
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

    fun setData(items: List<Menu>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemViewMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Menu) {
            binding.data = model

            binding.ivMenu.setImage(model.logoUrl)

            itemView.setOnClickListener {
                listener?.onItemClick(model)
                notifyDataSetChanged()
            }
        }
    }
}