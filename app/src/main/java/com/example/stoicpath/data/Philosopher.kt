package com.example.stoicpath.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Philosopher(
    @StringRes val nameResId: Int,       // String resource for name
    @StringRes val descriptionResId: Int, // String resource for description
    @DrawableRes val imageResId: Int      // Drawable resource for image
)
