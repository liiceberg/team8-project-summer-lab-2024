package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentIngredientsListBinding

class IngredientsListFragment : Fragment(R.layout.fragment_ingredients_list) {

    private var binding: FragmentIngredientsListBinding? = null

    private lateinit var ingList: ArrayList<String>
    private var isTextViewExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIngredientsListBinding.bind(view)

        ingList = ArrayList()

        binding?.run {

            addButton.setOnClickListener {
                val item = inputTextView.text.toString()

                if(item.isNotEmpty()) {
                    ingList.add(item)
                    expandableTextView.text = "${expandableTextView.text}-${inputTextView.text}\n"
                    inputTextView.setText("")
                }
            }

            expandableTextView.setOnClickListener {
                if (isTextViewExpanded) {
                    // Collapse the TextView
                    expandableTextView.maxLines = 1
                    isTextViewExpanded = false
                } else {
                    // Expand the TextView
                    expandableTextView.maxLines = Integer.MAX_VALUE
                    isTextViewExpanded = true
                }
            }

            val bundle = Bundle()
            bundle.apply {
                bundle.putStringArrayList("list", ingList);
            }
            println(ingList)
            fabSearch.setOnClickListener {

                findNavController().navigate(
                    R.id.action_ingredientsListFragment_to_recipeListFragment,
                    bundle
                )
            }


        }
    }

}