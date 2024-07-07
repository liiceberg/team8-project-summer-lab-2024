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
    private lateinit var ingredientsLV: ListView
    private lateinit var ingList: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIngredientsListBinding.bind(view)

        ingredientsLV = binding!!.idLVIngredients
        ingList = ArrayList()

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            ingList as List<String?>
        )

        ingredientsLV.adapter = adapter

        binding?.run {
            idBtnAdd.setOnClickListener {

                val item = idEdtItemName.text.toString()

                if (item.isNotEmpty()) {
                    ingList.add(item.trim())
                    adapter.notifyDataSetChanged()
                    idEdtItemName.setText("")
                }
            }

            val bundle = Bundle()
            bundle.apply {
                bundle.putStringArrayList("list", ingList);
            }

            fabSearch.setOnClickListener {
                findNavController().navigate(
                    R.id.action_ingredientsListFragment_to_recipeListFragment,
                    bundle
                )
            }

        }
    }

}