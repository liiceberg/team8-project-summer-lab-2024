package ru.kpfu.itis.summerlab.team8.cookup
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team8.cookup.productList.ProductsRepository
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ActivityMainBinding
import ru.kpfu.itis.summerlab.team8.cookup.di.ServiceLocator
import ru.kpfu.itis.summerlab.team8.cookup.recipe.RecipeRepository


class MainActivity : AppCompatActivity() {

    private var viewBinding: ActivityMainBinding? = null

    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            RecipeRepository.recipes = ServiceLocator.getDbInstance().recipeDao().getAll().toMutableList()
        }

        ProductsRepository.setProducts()

        installSplashScreen()

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        controller =
            (supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment).navController

        viewBinding?.apply {
            bottomNavigation.setupWithNavController(controller!!)
        }
    }
}
