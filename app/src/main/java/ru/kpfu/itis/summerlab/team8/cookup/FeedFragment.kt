package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentFeedBinding

class FeedFragment : Fragment(R.layout.fragment_feed){

    private var binding: FragmentFeedBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedBinding.bind(view)
        binding?.run {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}