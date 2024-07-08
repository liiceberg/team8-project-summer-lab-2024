package ru.kpfu.itis.summerlab.team8.cookup.wordAdaptee

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.Recipe
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
                    wordAdapter?.notifyItemChanged(wordList.size-1)
                    editText.setText("")
                }
            }
            sendButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(Intent.createChooser(intent, "Выбрать изображение"))
            }
            saveButton.setOnClickListener{
                val newRecipe = Recipe(
                    id = 0,
                    name = etName.text.toString(),
                    urlImage = "urlAddress",
                    description = etDescription.text.toString(),
                    ingredients = wordAdapter!!.getCheckedText(),
                    isFavourite = true,
                    instructions = etInstructions.text.toString()
                )
                lifecycleScope.launch{
                    ServiceLocator.getDbInstance().recipeDao().insert(newRecipe)
                }
                findNavController().navigateUp()
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