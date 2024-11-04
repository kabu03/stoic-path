package com.example.stoicpath.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.stoicpath.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("stoic_path_prefs", MODE_PRIVATE)

        // Check dark mode preference and apply theme
        val isDarkModeEnabled = isDarkModeEnabled()
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        setContentView(R.layout.activity_main)

        // Set up NavController with NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    // Make toggleDarkMode public so it can be accessed from fragments
    fun toggleDarkMode() {
        val isDarkMode = isDarkModeEnabled()
        setDarkModeEnabled(!isDarkMode)
        AppCompatDelegate.setDefaultNightMode(
            if (!isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun setDarkModeEnabled(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("DARK_MODE_ENABLED", enabled).apply()
    }

    private fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean("DARK_MODE_ENABLED", false)
    }
}
