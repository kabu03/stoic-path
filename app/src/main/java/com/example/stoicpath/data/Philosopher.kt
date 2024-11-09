package com.example.stoicpath.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Philosopher(
    val id: Int,                        // Unique identifier for the philosopher
    @StringRes val nameResId: Int,       // String resource for name
    @StringRes val descriptionResId: Int, // String resource for description
    @DrawableRes val imageResId: Int,     // Drawable resource for image
    var isFavorite: Boolean = false // Default is not a favorite
)
