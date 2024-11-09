package hu.bme.aut.qh8vdn.stoicpath.utils

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("stoic_path_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val FAVORITES_KEY = "FAVORITES_KEY"
        private const val DARK_MODE_KEY = "DARK_MODE_ENABLED"
    }

    private val KEY_DAILY_QUOTE = "daily_quote"
    private val KEY_QUOTE_AUTHOR = "quote_author"
    private val KEY_LAST_FETCHED_DATE = "last_fetched_date"

    // DARK MODE METHODS

    fun setDarkModeEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean(DARK_MODE_KEY, enabled).apply()
    }

    fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean(DARK_MODE_KEY, false)
    }

    // FAVORITES MANAGEMENT METHODS

    fun getFavoriteIds(): MutableSet<Int> {
        val favoriteStrings = sharedPreferences.getStringSet(FAVORITES_KEY, mutableSetOf()) ?: mutableSetOf()
        return favoriteStrings.map { it.toInt() }.toMutableSet()
    }

    fun saveFavoriteIds(favoriteIds: Set<Int>) {
        val favoriteStrings = favoriteIds.map { it.toString() }.toSet()
        sharedPreferences.edit().putStringSet(FAVORITES_KEY, favoriteStrings).apply()
    }

    fun addFavorite(id: Int) {
        val favorites = getFavoriteIds()
        favorites.add(id)
        saveFavoriteIds(favorites)
    }

    fun removeFavorite(id: Int) {
        val favorites = getFavoriteIds()
        favorites.remove(id)
        saveFavoriteIds(favorites)
    }

    fun isFavorite(id: Int): Boolean {
        return getFavoriteIds().contains(id)
    }

    // Method to get the stored quote
    fun getStoredQuote(): Pair<String?, String?> {
        val quote = sharedPreferences.getString(KEY_DAILY_QUOTE, null)
        val author = sharedPreferences.getString(KEY_QUOTE_AUTHOR, null)
        return quote to author
    }

    // Method to get the last fetched date
    fun getLastFetchedDate(): String? {
        return sharedPreferences.getString(KEY_LAST_FETCHED_DATE, null)
    }

    // Method to save the daily quote and current date
    fun saveDailyQuote(quote: String, author: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_DAILY_QUOTE, quote)
        editor.putString(KEY_QUOTE_AUTHOR, author)
        editor.putString(KEY_LAST_FETCHED_DATE, getCurrentDateString())
        editor.apply()
    }

    // Helper method to get today's date as a string
    fun getCurrentDateString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
