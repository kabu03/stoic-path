package com.example.stoicpath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.stoicpath.R
import com.example.stoicpath.activities.MainActivity

// import kotlinx.android.synthetic.main.fragment_philosophers.*

class PhilosophersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_philosophers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.navToDailyQuoteFromPhilosophers).setOnClickListener {
            findNavController().navigate(R.id.action_philosophers_to_daily_quote)
        }

        view.findViewById<Button>(R.id.navToPhilosophersList).setOnClickListener {
            findNavController().navigate(R.id.action_philosophers_to_detail)
        }

        view.findViewById<View>(R.id.darkModeToggle).setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }
    }
}

