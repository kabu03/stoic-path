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

class DailyQuoteFragment : Fragment() {

    private lateinit var quoteTextView: TextView
    private lateinit var authorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_daily_quote, container, false)

        // Initialize the TextViews
        quoteTextView = view.findViewById(R.id.dailyQuoteText)
        authorTextView = view.findViewById(R.id.dailyQuoteAuthor)

        // Fetch the daily quote
        fetchDailyQuote()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.navToDailyQuote).setOnClickListener {
            // Optional: Reload the Daily Quote fragment
            findNavController().navigate(R.id.dailyQuoteFragment)
        }

        view.findViewById<Button>(R.id.navToPhilosophersFromQuote).setOnClickListener {
            findNavController().navigate(R.id.action_daily_quote_to_philosophers)
        }

        view.findViewById<View>(R.id.darkModeToggle).setOnClickListener {
            (activity as? MainActivity)?.toggleDarkMode()
        }

    }

    private fun fetchDailyQuote() {
        RetrofitClient.quoteApiService.getDailyQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val quoteData = response.body()!!.data
                    quoteTextView.text = "\"${quoteData.quote}\""
                    authorTextView.text = "- ${quoteData.author}"
                } else {
                    Toast.makeText(context, "Failed to retrieve quote", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
