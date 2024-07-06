package ru.kpfu.itis.summerlab.team8.cookup.ProductList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemProductBinding

class ProductAdapter (
    private var list : MutableList<String>,
) : RecyclerView.Adapter<ProductHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.onBind(list[position])
    }
}