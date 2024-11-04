package com.example.stoicpath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.stoicpath.R
import androidx.navigation.fragment.findNavController
import com.example.stoicpath.activities.MainActivity

class PhilosopherDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_philosopher_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.navToDailyQuoteFromDetail).setOnClickListener {
            findNavController().navigate(R.id.action_detail_to_daily_quote)
        }

        view.findViewById<Button>(R.id.navToPhilosophersFromDetail).setOnClickListener {
            findNavController().navigate(R.id.action_detail_to_philosophers)
        }

        view.findViewById<View>(R.id.darkModeToggle).setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }
    }
}

