package com.example.stoicpath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stoicpath.R
import com.example.stoicpath.activities.MainActivity
import com.example.stoicpath.data.Philosopher
import com.example.stoicpath.data.PhilosopherRepository
import com.example.stoicpath.databinding.FragmentPhilosopherDetailBinding
import com.example.stoicpath.utils.SharedPreferencesHelper
import com.example.stoicpath.utils.navigateWithDebounce

class PhilosopherDetailFragment : Fragment() {

    private var _binding: FragmentPhilosopherDetailBinding? = null
    private val binding get() = _binding!!
    private val args: PhilosopherDetailFragmentArgs by navArgs() // Retrieve SafeArgs
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var philosopher: Philosopher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhilosopherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        // Retrieve the philosopher's ID from arguments
        val philosopherId = args.philosopherId
        loadPhilosopherDetails(philosopherId)

        // Set up navigation for buttons
        binding.navToDailyQuoteFromDetail.setOnClickListener {
            findNavController().navigate(R.id.action_detail_to_daily_quote)
        }

        binding.navToPhilosophersFromDetail.setOnClickListener {
            it.navigateWithDebounce(findNavController(), PhilosopherDetailFragmentDirections.actionDetailToPhilosophers())
        }

        binding.darkModeToggle.setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }
    }

    private fun loadPhilosopherDetails(id: Int) {
        // Fetch philosopher data from the repository with sharedPreferencesHelper
        philosopher = PhilosopherRepository.getPhilosopherById(id, sharedPreferencesHelper)
            ?: return  // Exit if philosopher is null

        // Update UI with philosopher's details
        binding.philosopherName.text = getString(philosopher.nameResId)
        binding.philosopherImage.setImageResource(philosopher.imageResId)
        binding.philosopherDescription.text = getString(philosopher.descriptionResId)

        // Set the initial state of the favorite icon
        updateFavoriteIcon()

        // Set up click listener for favorite icon
        binding.favoriteIcon.setOnClickListener {
            toggleFavorite()
        }
    }

    private fun toggleFavorite() {
        // Toggle favorite status
        philosopher.isFavorite = !philosopher.isFavorite

        // Update the shared preferences
        if (philosopher.isFavorite) {
            sharedPreferencesHelper.addFavorite(philosopher.id)
        } else {
            sharedPreferencesHelper.removeFavorite(philosopher.id)
        }

        // Update the icon immediately
        updateFavoriteIcon()
    }

    private fun updateFavoriteIcon() {
        binding.favoriteIcon.setImageResource(
            if (philosopher.isFavorite) R.drawable.ic_filled_star else R.drawable.ic_empty_star
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
