package com.example.stoicpath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stoicpath.R
import com.example.stoicpath.adapters.PhilosopherAdapter
import com.example.stoicpath.data.PhilosopherRepository
import com.example.stoicpath.databinding.FragmentPhilosophersBinding
import com.example.stoicpath.activities.MainActivity
import com.example.stoicpath.utils.SharedPreferencesHelper

class PhilosophersFragment : Fragment() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: PhilosopherAdapter
    private var _binding: FragmentPhilosophersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhilosophersBinding.inflate(inflater, container, false)

        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        // Initialize the list of philosophers with favorite statuses
        val philosophers = PhilosopherRepository.getPhilosophers(sharedPreferencesHelper)

        // Setup the adapter once with the philosophers list
        adapter = PhilosopherAdapter(philosophers, onFavoriteClick = { philosopher ->
            // Toggle the favorite status
            philosopher.isFavorite = !philosopher.isFavorite

            // Update shared preferences to reflect this change
            if (philosopher.isFavorite) {
                sharedPreferencesHelper.addFavorite(philosopher.id)
            } else {
                sharedPreferencesHelper.removeFavorite(philosopher.id)
            }

            // Update only the changed item in the adapter
            adapter.notifyItemChanged(philosophers.indexOf(philosopher))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView with adapter and layout manager
        binding.philosophersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.philosophersRecyclerView.adapter = adapter

        // Navigation button listeners
        binding.navToDailyQuoteFromPhilosophers.setOnClickListener {
            findNavController().navigate(R.id.action_philosophers_to_daily_quote)
        }

        // Modified navigation to PhilosophersFragment with conditional check
        binding.navToPhilosophersList.setOnClickListener {
            // Check if the current destination is already `philosophersFragment`
            if (findNavController().currentDestination?.id != R.id.philosophersFragment) {
                findNavController().navigate(R.id.action_philosophers_to_detail)
            }
        }

        // Dark mode toggle listener
        binding.darkModeToggle.setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
