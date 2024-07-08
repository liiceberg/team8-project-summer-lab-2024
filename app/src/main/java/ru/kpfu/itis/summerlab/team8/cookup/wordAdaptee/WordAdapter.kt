package ru.kpfu.itis.summerlab.team8.cookup.wordAdaptee
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemWordBinding

class WordAdapter(
    private val wordsList: ArrayList<String>
): RecyclerView.Adapter<WordHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            ItemWordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = wordsList[position]
        holder.onBind(word)
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }
    fun getCheckedText(): String {
        val checkedTextList = wordsList.map { it }
        return checkedTextList.joinToString(separator = ",")
    }
}