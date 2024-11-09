package hu.bme.aut.qh8vdn.stoicpath.utils

import android.os.SystemClock
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections

private var lastNavigationTime = 0L

fun View.navigateWithDebounce(navController: NavController, direction: NavDirections, debounceTime: Long = 500L) {
    val currentTime = SystemClock.elapsedRealtime()
    if (currentTime - lastNavigationTime > debounceTime) {
        lastNavigationTime = currentTime
        navController.navigate(direction)
    }
}
