package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentIngredientsListBinding

class IngredientsListFragment : Fragment(R.layout.fragment_ingredients_list) {

    private var binding: FragmentIngredientsListBinding? = null
    private var adapter: RecipeAdapter? = null

    private var ingList = mutableListOf<String>()
    private var isTextViewExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIngredientsListBinding.bind(view)

        initRecipeAdapter()

        binding?.run {

            addButton.setOnClickListener {
                val item = inputTextView.text.toString()

                if(item.isNotEmpty()) {
                    ingList.add(item.trim().lowercase())
                    expandableTextView.text = "${expandableTextView.text}${inputTextView.text}\n"
                    inputTextView.setText("")
                }

                adapter?.updateData(searchRecipe(ingList.toSet()))
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
        }
    }

    private fun initRecipeAdapter() {
        binding?.run {
            adapter = RecipeAdapter(
                list = listOf(),
                glide = Glide.with(this@IngredientsListFragment),
                onClick = {
                    val bundle = Bundle()
                    bundle.apply {
                        bundle.putLong("id", it.id);
                    }
                    findNavController().navigate(
                        R.id.action_ingredientsListFragment_to_recipeInfoFragment,
                        bundle
                    )
                }
            )
            rvRecipe.adapter = adapter
            rvRecipe.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun searchRecipe(list: Set<String>?): List<Recipe> =
        RecipeRepository.recipes
            .sortedByDescending { list!!.intersect(it.ingredients.lowercase().split(",").toSet()).size }
}