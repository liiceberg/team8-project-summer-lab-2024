package ru.kpfu.itis.summerlab.team8.cookup.ProductList

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemProductBinding

class ProductHolder(
    private val binding: ItemProductBinding,
) : ViewHolder(binding.root) {

    init {
        binding.apply {
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    buttonView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    buttonView.paintFlags = 0
                }
            }
        }
    }

    fun onBind(product: String) {
        binding.apply {
            checkbox.text = product
        }
    }
}