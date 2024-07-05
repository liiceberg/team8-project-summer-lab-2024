package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewBinding : ActivityMainBinding? = null

    private var controller : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        controller = (supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment).navController

        viewBinding?.apply {
            bottomNavigation.setupWithNavController(controller!!)
        }
    }
}
