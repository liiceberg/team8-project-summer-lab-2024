package ru.kpfu.itis.summerlab.team8.cookup.wordAdaptee

import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemWordBinding

class WordHolder(
    private val binding : ItemWordBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(word: String){
        binding.apply {
            itemWord.text = word
        }
    }
}
