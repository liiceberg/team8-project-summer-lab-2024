package ru.kpfu.itis.summerlab.team8.cookup.wordAdaptee

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.recipe.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.recipe.RecipeRepository
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentNewRecipeBinding
import ru.kpfu.itis.summerlab.team8.cookup.di.ServiceLocator


class NewRecipeFragment : Fragment(R.layout.fragment_new_recipe) {

    private var binding: FragmentNewRecipeBinding? = null
    private var wordAdapter : WordAdapter? = null
    private val wordList = ArrayList<String>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewRecipeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        val recipeImage = binding!!.recipeImage

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                recipeImage.setImageURI(selectedImageUri)
            }
        }

        binding?.run {
            addButton.setOnClickListener {
                val word = editText.text.toString()
                if (word.isNotEmpty()) {
                    wordList.add(word)
                    wordAdapter?.notifyDataSetChanged()
                    editText.setText("")
                }
            }
            sendButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(Intent.createChooser(intent, "Выбрать изображение"))
            }
            saveButton.setOnClickListener{
                val name = etName.text.toString()
                val description = etDescription.text.toString()
                val ingredients = wordAdapter?.getCheckedText().toString()
                val instructions = etInstructions.text.toString()

                var isValid = true

                if(name.isEmpty()){
                    etName.setBackgroundColor(Color.RED)
                    isValid = false
                } else {
                    etName.setBackgroundColor(Color.TRANSPARENT)
                }

                if(description.isEmpty()){
                    etDescription.setBackgroundColor(Color.RED)
                    isValid = false
                } else {
                    etDescription.setBackgroundColor(Color.TRANSPARENT)
                }

                if(instructions.isEmpty()){
                    etInstructions.setBackgroundColor(Color.RED)
                    isValid = false
                } else {
                    etInstructions.setBackgroundColor(Color.TRANSPARENT)
                }

                if(isValid){
                    val newRecipe = Recipe(
                        id = 0,
                        name = name,
                        urlImage = "urlAddress",
                        description = description,
                        ingredients = ingredients,
                        isFavourite = true,
                        instructions = instructions
                    )
                    RecipeRepository.recipes.add(newRecipe)
                    lifecycleScope.launch{
                        ServiceLocator.getDbInstance().recipeDao().insert(newRecipe)
                    }
                    findNavController().navigateUp()
                } else{
                    Snackbar.make(saveButton, "Заполните рецепт полностью", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun initAdapter() {
        wordAdapter = WordAdapter(wordList)
        binding?.run {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = wordAdapter

            newRecipeToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}