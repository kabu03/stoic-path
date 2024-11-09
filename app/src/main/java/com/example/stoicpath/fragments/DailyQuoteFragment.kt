package com.example.stoicpath.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stoicpath.R
import com.example.stoicpath.network.RetrofitClient
import com.example.stoicpath.data.QuoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.fragment.findNavController
import com.example.stoicpath.activities.MainActivity
import com.example.stoicpath.utils.SharedPreferencesHelper
import com.example.stoicpath.utils.navigateWithDebounce

class DailyQuoteFragment : Fragment() {

    private lateinit var quoteTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_quote, container, false)

        // Initialize views
        quoteTextView = view.findViewById(R.id.dailyQuoteText)
        authorTextView = view.findViewById(R.id.dailyQuoteAuthor)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        // Load or fetch the daily quote
        loadOrFetchDailyQuote()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.navToDailyQuote).setOnClickListener {
            findNavController().navigate(R.id.dailyQuoteFragment)
        }

        view.findViewById<Button>(R.id.navToPhilosophersFromQuote).setOnClickListener {
            it.navigateWithDebounce(findNavController(), DailyQuoteFragmentDirections.actionDailyQuoteToPhilosophers())
        }

        view.findViewById<View>(R.id.darkModeToggle).setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }
    }

    private fun loadOrFetchDailyQuote() {
        val currentDateString = sharedPreferencesHelper.getLastFetchedDate()
        val todayDate = sharedPreferencesHelper.getCurrentDateString()

        if (currentDateString == todayDate) {
            // Load the stored quote if it's from today
            val (storedQuote, storedAuthor) = sharedPreferencesHelper.getStoredQuote()
            if (storedQuote != null && storedAuthor != null) {
                quoteTextView.text = "\"$storedQuote\""
                authorTextView.text = "- $storedAuthor"
                return
            }
        }

        // If no valid stored quote, fetch a new one
        fetchDailyQuote()
    }

    private fun fetchDailyQuote() {
        RetrofitClient.quoteApiService.getDailyQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val quoteData = response.body()!!.data
                    val quoteText = quoteData.quote
                    val author = quoteData.author

                    quoteTextView.text = "\"$quoteText\""
                    authorTextView.text = "- $author"

                    // Save the quote and author with today's date
                    sharedPreferencesHelper.saveDailyQuote(quoteText, author)
                } else {
                    Toast.makeText(context, "Failed to retrieve quote", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
